package me.vincentlin.bookstore.service;

import me.vincentlin.bookstore.model.User;

/**
 * Created by Vincent on 2017/3/25.
 */
public interface UserService {
    void createUser(User user);

    User findByUsername(String username);

    void save(User user);

}
