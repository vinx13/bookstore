package me.vincentlin.bookstore.service;

import me.vincentlin.bookstore.dao.OrderEntryRepository;
import me.vincentlin.bookstore.dao.OrderRepository;
import me.vincentlin.bookstore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by Vincent on 2017/4/12.
 */

public interface OrderService {
    Order checkout(User user, List<CartItem> items);

    Order pay(Long orderId);

    Order cancel(Long orderId);

    Order complete(Long orderId);

    List<Order> findByUser(Principal principal);

    List<Order> findByUser(Long userId);

    List<Order> findByUser(User user);

    List<Order> findByBook(Long bookId);

    List<Order> findByBook(Book book);

    List<Order> findByGenre(Long genreId);

    List<Order> findByGenre(Genre genre);

    List<Order> findBetweenDate(Date start, Date end);
}
