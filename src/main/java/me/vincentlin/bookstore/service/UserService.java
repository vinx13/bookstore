package me.vincentlin.bookstore.service;

import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.model.UserProfile;

import java.security.Principal;

/**
 * Created by Vincent on 2017/3/25.
 */
public interface UserService {
    void createUser(User user);
    User findByUsername(String username);
    User find(Principal principal);

    UserProfile getProfile(Principal principal);
}
