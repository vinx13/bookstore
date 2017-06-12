package me.vincentlin.bookstore.service.impl;

import me.vincentlin.bookstore.common.UnloginedException;
import me.vincentlin.bookstore.dao.*;
import me.vincentlin.bookstore.model.*;
import me.vincentlin.bookstore.service.OrderService;
import me.vincentlin.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public Order checkout(User user, List<CartItem> items) {
        if (items == null || items.isEmpty()) return null;

        Order order = new Order();
        List<OrderEntry> entries = new ArrayList<>(items.size());

        for (CartItem item : items) {
            OrderEntry entry = new OrderEntry();
            entry.setBook(item.getBook());
            entry.setQuantity(item.getQuantity());
            BigDecimal price = item.getBook().getPrice();
            entry.setUnitPrice(price);
            entry.setOrder(order);
            entries.add(entry);
        }
        order.setStatus(Order.OrderStatus.UnPaid);
        order.setEntries(entries);
        order.setUser(user);
        orderRepository.save(order);

        return order;
    }

    @Override
    public List<Order> findByUser(Principal principal) {
        User user = userService.find(principal);
        if (user == null) throw new UnloginedException();
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
    public Order pay(Long orderId) {
        return updateOrder(orderId,Order.OrderStatus.Paid);
    }

    @Override
    public Order cancel(Long orderId) {
        return updateOrder(orderId, Order.OrderStatus.Canceled);
    }

    @Override
    public Order complete(Long orderId) {
        return updateOrder(orderId, Order.OrderStatus.Completed);
    }

    private Order updateOrder(Long orderId, Order.OrderStatus status){
        Order order = orderRepository.findOne(orderId);
        if(order==null)throw new ResourceNotFoundException();
        order.setStatus(status);
        orderRepository.save(order);
        return order;
    }
}
