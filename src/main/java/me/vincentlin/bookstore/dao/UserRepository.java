package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vincent on 2017/3/25.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameStartsWith(@Param("name") String name);
}
