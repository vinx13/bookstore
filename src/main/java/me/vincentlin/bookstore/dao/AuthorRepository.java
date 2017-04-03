package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/4/1.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
