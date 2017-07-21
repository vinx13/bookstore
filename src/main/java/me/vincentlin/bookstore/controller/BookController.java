package me.vincentlin.bookstore.controller;

import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.BookForm;
import me.vincentlin.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books/update")
public class BookController {
    @Autowired BookService bookService;
    @PostMapping
    ResponseEntity<?> updateOrCreate(@RequestBody BookForm form) {
        Book book = bookService.parse(form);
        if(book == null ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(book);
    }
}
