package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/4/1.
 */
@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}
