package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/4/2.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}
