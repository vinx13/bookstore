package me.vincentlin.bookstore.service.impl;

import me.vincentlin.bookstore.dao.RoleRepository;
import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.Role;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Vincent on 2017/4/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void createUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.addRole(userRole);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
