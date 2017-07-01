package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.common.Utils;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.CartService;
import me.vincentlin.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    Utils utils;

    public OrderController() {
    }

    @PostMapping("/api/checkout")
    public Order newOrder(Principal principal) {
        User user = utils.getCurrentUser(principal);
        List<CartItem> items = cartService.getItems(user);
        Order order = orderService.checkout(user, items);
        cartService.removeAll(user);
        return order;
    }

    @PostMapping("/api/checkout/{id}/pay")
    public ResponseEntity<?> payOrder(Principal principal, @PathVariable("id") Long orderId) {
        orderService.pay(principal, orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/checkout/{id}/cancel")
    public ResponseEntity<?> cancelOrder(Principal principal, @PathVariable("id") Long orderId) {
        orderService.cancel(principal, orderId);
        return ResponseEntity.noContent().build();
    }


}
