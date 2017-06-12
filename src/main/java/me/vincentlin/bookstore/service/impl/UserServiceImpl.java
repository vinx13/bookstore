package me.vincentlin.bookstore.service.impl;

import me.vincentlin.bookstore.common.UnloginedException;
import me.vincentlin.bookstore.common.Utils;
import me.vincentlin.bookstore.dao.RoleRepository;
import me.vincentlin.bookstore.dao.UserProfileRepository;
import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.Role;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.model.UserProfile;
import me.vincentlin.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

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
    private UserProfileRepository userProfileRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    Utils utils;

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

    @Override
    public User find(Principal principal) {
        return utils.getCurrentUser(principal);
    }

    @Override
    public UserProfile getProfile(Principal principal) {
        User user = find(principal);
        if(user==null)
            throw new UnloginedException();
        return userProfileRepository.findOne(user.getId());
    }
}
