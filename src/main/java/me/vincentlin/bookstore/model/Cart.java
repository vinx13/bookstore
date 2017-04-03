package me.vincentlin.bookstore.model;

import javax.persistence.*;

/**
 * Created by Vincent on 2017/4/1.
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Book book;

    Long quantity;
}
