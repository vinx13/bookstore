package me.vincentlin.bookstore.model.projection;

import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.OrderEntry;
import me.vincentlin.bookstore.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by Vincent on 2017/4/17.
 */
@Projection(types = {OrderEntry.class})
public interface OrderEntryExcerpt {
    Book getBook();
    Long getUnitPrice();
    Long getQuantity();
}
