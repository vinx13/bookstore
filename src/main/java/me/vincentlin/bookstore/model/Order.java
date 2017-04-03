package me.vincentlin.bookstore.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Vincent on 2017/4/1.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn
    User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderEntry> entries;
}
