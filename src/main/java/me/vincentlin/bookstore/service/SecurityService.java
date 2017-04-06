package me.vincentlin.bookstore.service;

/**
 * Created by Vincent on 2017/3/30.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}