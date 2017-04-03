package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/4/1.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
}
