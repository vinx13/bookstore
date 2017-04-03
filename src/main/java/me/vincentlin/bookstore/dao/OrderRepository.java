package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/3/25.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
