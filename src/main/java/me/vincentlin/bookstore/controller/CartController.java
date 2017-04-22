package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.dao.BookRepository;
import me.vincentlin.bookstore.dao.CartRepository;
import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.*;
import me.vincentlin.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vincent on 2017/4/5.
 */
@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;

    @GetMapping("/api/user/cart")
    public List<CartItem> getItems(Principal principal) {
        User user = getCurrentUser(principal);
        return cartService.findByUser(user);
    }

    @GetMapping("/api/user/cart/{bookId}")
    public ResponseEntity<?> addOne(Principal principal, @PathVariable Long bookId) {
        User user = getCurrentUser(principal);
        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        cartService.addOne(user, book);
        List<CartItem> items = cartService.getItems(user);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/api/user/cart/{bookId}")
    public ResponseEntity<?> setItem(Principal principal, @PathVariable Long bookId, @RequestParam("quantity") Long quantity) {
        User user = getCurrentUser(principal);
        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        cartService.setItem(user, book, quantity);
        List<CartItem> items = cartService.getItems(user);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/api/user/cart/{bookId}")
    public ResponseEntity<?> removeOne(Principal principal, @PathVariable Long bookId) {
        User user = getCurrentUser(principal);
        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        cartService.removeOne(user, book);
        List<CartItem> items = cartService.getItems(user);
        return ResponseEntity.ok(items);
    }

    private User getCurrentUser(Principal principal) {
        if (principal == null) return null;
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }
}
