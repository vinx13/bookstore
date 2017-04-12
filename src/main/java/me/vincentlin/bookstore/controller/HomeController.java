package me.vincentlin.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent on 2017/4/2.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String getStoreIndex() {
        return "store/index";
    }

    @RequestMapping("/admin")
    public String getAdminIndex() {
        return "admin/index";
    }

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }
}
