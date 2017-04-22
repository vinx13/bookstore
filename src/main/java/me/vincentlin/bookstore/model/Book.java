package me.vincentlin.bookstore.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Vincent on 2017/3/25.
 */

@Entity
public class Book {

    @Id
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    @GeneratedValue
    private Long id;
    private String name;
    private String isbn;
    private String description;
    private String image;
    private Long inventory;
    @Column(columnDefinition = "DECIMAL(6,2)")
    private Double price;
    @ManyToMany
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    private Genre genre;

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
