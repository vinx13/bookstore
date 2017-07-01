package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.Order;
import me.vincentlin.bookstore.model.projection.OrderExcerpt;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.NamedStoredProcedureQuery;
import java.util.List;

/**
 * Created by Vincent on 2017/3/25.
 */
@RepositoryRestResource(excerptProjection = OrderExcerpt.class)
public interface OrderRepository extends JpaRepository<Order, Long>,JpaSpecificationExecutor {
    @Query("select o from Order o join o.entries e where e.book.id = ?1")
    List<Order> findByBook(Long bookId);

    @Query("select o from Order o join o.entries e where e.book.genre.id = ?1")
    List<Order> findByGenre(Long genreId);


}
