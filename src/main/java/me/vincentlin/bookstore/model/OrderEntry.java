package me.vincentlin.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Vincent on 2017/3/25.
 */

@Entity
public class OrderEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Book book;

    @Column(columnDefinition = "DECIMAL(6,2)")
    private BigDecimal unitPrice;
    private Long quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
