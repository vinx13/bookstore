package me.vincentlin.bookstore.service;

import me.vincentlin.bookstore.dao.OrderEntryRepository;
import me.vincentlin.bookstore.dao.OrderRepository;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vincent on 2017/4/12.
 */

public interface OrderService {
    Order checkout(User user, List<CartItem> items);
}
