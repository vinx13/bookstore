package me.vincentlin.bookstore.service.impl;

import me.vincentlin.bookstore.dao.BookRepository;
import me.vincentlin.bookstore.dao.OrderEntryRepository;
import me.vincentlin.bookstore.dao.OrderRepository;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.OrderEntry;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    public Order checkout(User user, List<CartItem> items) {
        List<OrderEntry> entries = new ArrayList<>(items.size());
        for (CartItem item : items) {
            OrderEntry entry = new OrderEntry();
            entry.setBook(item.getBook());
            entry.setQuantity(item.getQuantity());
            Double price = item.getBook().getPrice();
            entry.setUnitPrice(price);
            entries.add(entry);
        }
        Order order = new Order();
        order.setEntries(entries);
        order.setUser(user);
        orderEntryRepository.save(entries);
        orderRepository.save(order);
        return order;
    }
}
