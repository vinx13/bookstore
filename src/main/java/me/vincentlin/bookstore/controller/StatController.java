package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.model.Stat;
import me.vincentlin.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    @Autowired
    OrderService orderService;

    @GetMapping("/api/stat")
    public Stat getStat(@RequestParam(required = false) Long userId,
                        @RequestParam(required = false) Long genreId,
                        @RequestParam(required = false) Long bookId) {
        return orderService.findStat(userId, genreId, bookId, null, null);
    }
}
