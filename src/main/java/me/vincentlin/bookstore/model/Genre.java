package me.vincentlin.bookstore.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Vincent on 2017/3/25.
 */

@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
