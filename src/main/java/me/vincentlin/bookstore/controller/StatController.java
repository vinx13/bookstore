package me.vincentlin.bookstore.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import me.vincentlin.bookstore.model.Stat;
import me.vincentlin.bookstore.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    @Autowired
    OrderService orderService;

    @GetMapping("/api/stat")
    public ResponseEntity<?> getStat(@RequestParam(required = false) Long userId,
        @RequestParam(required = false) Long genreId,
        @RequestParam(required = false) Long bookId,
        @RequestParam(required = false, name = "startDate") String start,
        @RequestParam(required = false, name = "endDate") String end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date startDate = !StringUtils.isEmpty(start) ? format.parse(start) : null;
            Date endDate = !StringUtils.isEmpty(end) ? format.parse(end):null;

            Stat stat = orderService.findStat(userId, genreId, bookId, startDate, endDate);
            return ResponseEntity.ok(stat);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
