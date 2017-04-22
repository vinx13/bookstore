package me.vincentlin.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Vincent on 2017/4/3.
 */

@RestController
public class AuthController {
    @GetMapping("/api/user")
    public ResponseEntity<?> user(Principal user) {
        if (user != null)
            return ResponseEntity.ok(user);
        return ResponseEntity.status(401).build();
    }
}
