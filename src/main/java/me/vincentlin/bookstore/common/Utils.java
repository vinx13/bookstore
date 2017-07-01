package me.vincentlin.bookstore.common;

import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class Utils {
    @Autowired
    UserRepository userRepository;

    public User getCurrentUser(Principal principal) {
        if(principal == null)

            throw new UnloginedException();
        User user = userRepository.findByUsername(principal.getName());
        return user;
    }
}
