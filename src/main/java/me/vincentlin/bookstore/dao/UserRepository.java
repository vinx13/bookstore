package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/3/25.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
