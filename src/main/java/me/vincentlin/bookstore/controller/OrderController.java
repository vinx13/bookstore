package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.CartService;
import me.vincentlin.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by Vincent on 2017/4/12.
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/checkout")
    public Order newOrder(Principal principal) {
        User user=getCurrentUser(principal);
        List<CartItem> items = cartService.getItems(user);
        Order order = orderService.checkout(user, items);
        cartService.removeAll(user);
        return order;
    }

    private User getCurrentUser(Principal principal) {
        if (principal == null) return null;
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }
}
