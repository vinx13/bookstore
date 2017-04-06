package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.dao.BookRepository;
import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/api/user/cart")
    public List<CartItem> getItems(Principal principal) {
        User user = getCurrentUser(principal);
        return cartService.getItems(user);
    }

    @PostMapping("/api/user/cart")
    public ResponseEntity<?> postItem(Principal principal, @RequestParam("book_id") Long bookId, @RequestParam("quantity") Long quantity) {
        User user = getCurrentUser(principal);
        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        cartService.setItem(user, book, quantity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/user/cart{/id}")
    public ResponseEntity<?> deleteItem(Principal principal, @RequestParam("id") Long id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }

    private User getCurrentUser(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }
}
