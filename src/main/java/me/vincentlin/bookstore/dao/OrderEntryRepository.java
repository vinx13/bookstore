package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.OrderEntry;
import me.vincentlin.bookstore.model.projection.OrderEntryExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Vincent on 2017/4/1.
 */
@RepositoryRestResource(excerptProjection = OrderEntryExcerpt.class)
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}
