package me.vincentlin.bookstore.service;

import me.vincentlin.bookstore.dao.BookRepository;
import me.vincentlin.bookstore.dao.CartRepository;
import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vincent on 2017/4/5.
 */
public interface CartService {
    List<CartItem> getItems(User user);

    void addOne(User user, Book book);

    void setItem(User user, Book book, Long quantity);

    void removeOne(User user, Book book);

    //void removeOne(Long id);

    void removeAll(User user);

    List<CartItem> findByUser(User user);
}
