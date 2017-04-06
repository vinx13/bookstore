package me.vincentlin.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Vincent on 2017/4/1.
 */
@Entity
public class CartItem {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn
    Book book;

    Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
