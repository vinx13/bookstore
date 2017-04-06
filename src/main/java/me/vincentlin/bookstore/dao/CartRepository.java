package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vincent on 2017/4/1.
 */
@Repository
public interface CartRepository extends JpaRepository<CartItem,Long>{
    CartItem findByUserIdAndBookId(Long userId, Long bookId);
    List<CartItem> findByUserId(Long userId);
}
