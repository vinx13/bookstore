package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.dao.UserRepository;
import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.SecurityService;
import me.vincentlin.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        String plainPassword = userForm.getPassword();

        userService.createUser(userForm);
        securityService.autoLogin(userForm.getUsername(), plainPassword);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.findLoggedInUsername() != null) {
            return "redirect:/";
        }
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}