package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.projection.OrderExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Vincent on 2017/3/25.
 */
@RepositoryRestResource(excerptProjection = OrderExcerpt.class)
public interface OrderRepository extends JpaRepository<Order, Long> {
}
