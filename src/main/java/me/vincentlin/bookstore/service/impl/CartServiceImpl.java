package me.vincentlin.bookstore.service.impl;

import me.vincentlin.bookstore.dao.BookRepository;
import me.vincentlin.bookstore.dao.CartRepository;
import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.CartService;
import me.vincentlin.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * Created by Vincent on 2017/4/5.
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<CartItem> getItems(User user) {
        return cartRepository.findByUserId(user.getId());
    }

    @Override
    public void addOne(User user, Book book) {
        CartItem item = cartRepository.findByUserIdAndBookId(user.getId(), book.getId());
        if (item == null) {
            item = new CartItem();
            item.setUser(user);
            item.setBook(book);
            item.setQuantity(0L);
        }
        item.setQuantity(item.getQuantity() + 1);
        cartRepository.save(item);
    }

    @Override
    public void setItem(User user, Book book, Long quantity) {
        CartItem item = cartRepository.findByUserIdAndBookId(user.getId(), book.getId());
        if (quantity == 0) {
            cartRepository.delete(item);
            return;
        }
        if (item == null) {
            item = new CartItem();
            item.setUser(user);
            item.setBook(book);
        }
        item.setQuantity(quantity);
        cartRepository.save(item);
    }


    @Override
    public void removeOne(User user, Book book) {
        CartItem item = cartRepository.findByUserIdAndBookId(user.getId(), book.getId());
        cartRepository.delete(item);
        if (item.getQuantity() == 1) {
            cartRepository.delete(item);
        } else {
            item.setQuantity(item.getQuantity() - 1);
            cartRepository.save(item);
        }
    }

    @Override
    public void removeAll(User user) {
        List<CartItem> items = cartRepository.findByUserId(user.getId());
        cartRepository.delete(items);
    }

    @Override
    public List<CartItem> findByUser(User user) {
        return cartRepository.findByUserId(user.getId());
    }

    @Override
    public List<CartItem> findByUser(Principal principal) {
        User user = userService.find(principal);
        return findByUser(user);
    }

    @Override
    public void addOne(Principal principal, Long bookId) {
        User user = userService.find(principal);
        Book book = bookRepository.findOne(bookId);
        if (book == null || user == null) {
            throw new ResourceNotFoundException();
        }
        addOne(user, book);
    }

    @Override
    public List<CartItem> getItems(Principal principal) {
        User user = userService.find(principal);
        return getItems(user);
    }

    @Override
    public void setItem(Principal principal, Long bookId, Long quantity) {
        User user = userService.find(principal);
        Book book = bookRepository.findOne(bookId);
        if (book == null || user == null) {
            throw new ResourceNotFoundException();
        }
        setItem(user, book, quantity);
    }

    @Override
    public void removeOne(Principal principal, Long bookId) {
        User user = userService.find(principal);
        Book book = bookRepository.findOne(bookId);
        if (book == null || user == null) {
            throw new ResourceNotFoundException();
        }
        removeOne(user, book);
    }
}
