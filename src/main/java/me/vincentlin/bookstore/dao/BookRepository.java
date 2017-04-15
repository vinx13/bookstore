package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/3/25.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
