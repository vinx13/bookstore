package me.vincentlin.bookstore.service;

import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.BookForm;

public interface BookService {
    Book parse(BookForm form);
}
