package me.vincentlin.bookstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 2017/4/1.
 */
@Entity
@Table(name="\"Order\"")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    User user;

    @OneToMany
    private List<OrderEntry> entries;

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

    public List<OrderEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<OrderEntry> entries) {
        this.entries = entries;
    }

}
