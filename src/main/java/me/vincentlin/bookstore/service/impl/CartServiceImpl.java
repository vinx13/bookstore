package me.vincentlin.bookstore.service.impl;

import me.vincentlin.bookstore.dao.BookRepository;
import me.vincentlin.bookstore.dao.CartRepository;
import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.CartItem;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.CartService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vincent on 2017/4/5.
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;


    @Override
    public List<CartItem> getItems(User user) {
        return cartRepository.findByUserId(user.getId());
    }

    @Override
    public void setItem(User user, Book book, Long quantity) {
        if (quantity == 0) {
            removeItem(user, book);
        } else {
            createOrUpdate(user, book, quantity);
        }
    }

    private void createOrUpdate(User user, Book book, Long quantity) {
        CartItem item = cartRepository.findByUserIdAndBookId(user.getId(), book.getId());
        if (item == null) {
            item = new CartItem();
            item.setUser(user);
            item.setBook(book);
        }
        item.setQuantity(quantity);
        cartRepository.save(item);
    }

    @Override
    public void removeItem(User user, Book book) {
        CartItem item = cartRepository.findByUserIdAndBookId(user.getId(), book.getId());
        cartRepository.delete(item);
    }
}
