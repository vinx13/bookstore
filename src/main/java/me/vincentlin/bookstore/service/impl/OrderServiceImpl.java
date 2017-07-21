package me.vincentlin.bookstore.service.impl;

import me.vincentlin.bookstore.common.OrderException;
import me.vincentlin.bookstore.common.OrderPermissionException;
import me.vincentlin.bookstore.common.UnloginedException;
import me.vincentlin.bookstore.common.Utils;
import me.vincentlin.bookstore.dao.*;
import me.vincentlin.bookstore.model.*;
import me.vincentlin.bookstore.service.OrderService;
import me.vincentlin.bookstore.service.UserService;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vincent on 2017/4/12.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderEntryRepository orderEntryRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    Utils utils;

    @Autowired
    DataSource dataSource;

    @Override
    @Transactional
    public Order checkout(User user, List<CartItem> items) {
        if (items == null || items.isEmpty())
            return null;

        Order order = new Order();
        List<OrderEntry> entries = new ArrayList<>(items.size());

        for (CartItem item : items) {
            OrderEntry entry = new OrderEntry();
            entry.setBook(item.getBook());
            entry.setQuantity(item.getQuantity());
            BigDecimal price = item.getBook().getPrice();
            entry.setUnitPrice(price);
            entry.setOrder(order);

            if (!validateEntry(entry))
                return null;
            entries.add(entry);
        }
        order.setStatus(Order.OrderStatus.UnPaid);
        order.setEntries(entries);
        order.setUser(user);
        orderRepository.save(order);

        return order;
    }

    private boolean validateEntry(OrderEntry entry) {
        if (entry.getQuantity() <= 0)
            return false;
        return true;
    }

    @Override
    public List<Order> findByUser(Principal principal) {
        User user = userService.find(principal);
        if (user == null)
            throw new UnloginedException();
        return findByUser(user);
    }

    @Override
    public List<Order> findByUser(User user) {
        if (user == null)
            throw new ResourceNotFoundException();
        return user.getOrders();
    }

    @Override
    public List<Order> findByUser(Long userId) {
        User user = userRepository.getOne(userId);
        return findByUser(user);
    }

    @Override
    public List<Order> findByBook(Long bookId) {
        Book book = bookRepository.findOne(bookId);
        return findByBook(book);
    }

    @Override
    public List<Order> findByBook(Book book) {
        if (book == null)
            throw new ResourceNotFoundException();
        return orderRepository.findByBook(book.getId());
    }

    @Override
    public List<Order> findByGenre(Long genreId) {
        Genre genre = genreRepository.findOne(genreId);
        return findByGenre(genre);
    }

    @Override
    public List<Order> findByGenre(Genre genre) {
        if (genre == null)
            throw new ResourceNotFoundException();
        return orderRepository.findByGenre(genre.getId());
    }

    @Override
    public List<Order> findBetweenDate(Date start, Date end) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public Order pay(Principal principal, Long orderId) {
        User user = utils.getCurrentUser(principal);
        Order order = orderRepository.findOne(orderId);
        if (order.getUser().getId() != user.getId())
            throw new OrderPermissionException();
        return updateOrder(orderId, Order.OrderStatus.Paid);
    }

    @Override
    @Transactional
    public Order cancel(Principal principal, Long orderId) {
        User user = utils.getCurrentUser(principal);
        Order order = orderRepository.findOne(orderId);
        if (!user.isAdmin() && order.getUser().getId() != user.getId())
            throw new OrderPermissionException();
        return updateOrder(orderId, Order.OrderStatus.Canceled);
    }

    @Override
    @Transactional
    public Order complete(Principal principal, Long orderId) {
        User user = utils.getCurrentUser(principal);
        Order order = getOr404(orderId);

        if (!user.isAdmin())
            throw new OrderPermissionException();

        if (order.getStatus().equals(Order.OrderStatus.Completed))
            throw new OrderException("Order is already completed");
        if (order.getStatus().equals(Order.OrderStatus.UnPaid))
            throw new OrderException("Order is not paid");
        if (!checkInventory(order))
            throw new OrderException("Inventory not available");

        updateInventory(order);

        return updateOrder(orderId, Order.OrderStatus.Completed);
    }

    private void updateInventory(Order order) {
        for(OrderEntry entry: order.getEntries()) {
            Book book = entry.getBook();
            Long inventory = book.getInventory() - entry.getQuantity();
            book.setInventory(inventory);
            bookRepository.save(book);
        }
    }

    private boolean checkInventory(Long orderId) {
        Order order = getOr404(orderId);
        return checkInventory(order);
    }

    private boolean checkInventory(Order order) {

        for (OrderEntry entry : order.getEntries()) {
            if (entry.getQuantity() > entry.getBook().getInventory()) {
                return false;
            }
        }
        return true;
    }

    private Order updateOrder(Long orderId, Order.OrderStatus status) {
        Order order = getOr404(orderId);
        order.setStatus(status);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Stat findStat(@Nullable Long userId, @Nullable Long bookId, @Nullable Long genreId, @Nullable Date start,
        @Nullable Date end) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("stat");
        /* IN genre_id LONG, IN user_id LONG, IN book_id LONG, IN start_date DATETIME, IN end_date DATETIME */
        jdbcCall.declareParameters(
            new SqlParameter("genre_id", Types.BIGINT),
            new SqlParameter("user_id", Types.BIGINT),
            new SqlParameter("book_id", Types.BIGINT),
            new SqlParameter("start_date", Types.DATE),
            new SqlParameter("end_date", Types.DATE),
            new SqlOutParameter("sales_count", Types.BIGINT),
            new SqlOutParameter("sales_amount", Types.DECIMAL)
        );
        Map<String, Object> out = jdbcCall.execute(genreId, userId, bookId, start, end);
        Stat stat = new Stat();
        stat.setSaleCount(Long.valueOf((String)out.get("sale_count")));
        BigDecimal amount = (BigDecimal)out.get("sale_amount");
        stat.setSaleAmount(amount != null ? amount : new BigDecimal(0));
        return stat;
    }

    private Order getOr404(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        if (order != null)
            return order;
        throw new ResourceNotFoundException();
    }
}
