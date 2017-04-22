package me.vincentlin.bookstore.model.projection;

import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.OrderEntry;
import me.vincentlin.bookstore.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by Vincent on 2017/4/17.
 */
@Projection(types = {Order.class})
public interface OrderExcerpt {
    Long getId();
    List<OrderEntry> getEntries();
    User getUser();
}
