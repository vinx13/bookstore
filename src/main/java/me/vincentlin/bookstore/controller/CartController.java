package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.model.*;
import me.vincentlin.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Vincent on 2017/4/5.
 */
@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/api/user/cart")
    public List<CartItem> getItems(Principal principal) {
        return cartService.findByUser(principal);
    }

    @GetMapping("/api/user/cart/{bookId}")
    public List<CartItem> addOne(Principal principal, @PathVariable Long bookId) {
        cartService.addOne(principal, bookId);
        List<CartItem> items = cartService.getItems(principal);
        return items;
    }

    @PutMapping("/api/user/cart/{bookId}")
    public List<CartItem> setItem(Principal principal, @PathVariable Long bookId, @RequestParam("quantity") Long quantity) {
        cartService.setItem(principal, bookId, quantity);
        List<CartItem> items = cartService.getItems(principal);
        return items;
    }

    @DeleteMapping("/api/user/cart/{bookId}")
    public List<CartItem> removeOne(Principal principal, @PathVariable Long bookId) {
        cartService.removeOne(principal, bookId);
        List<CartItem> items = cartService.getItems(principal);
        return items;
    }

}
