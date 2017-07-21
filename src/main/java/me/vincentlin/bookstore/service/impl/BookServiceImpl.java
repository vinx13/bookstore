package me.vincentlin.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import me.vincentlin.bookstore.dao.AuthorRepository;
import me.vincentlin.bookstore.dao.BookRepository;
import me.vincentlin.bookstore.dao.GenreRepository;
import me.vincentlin.bookstore.model.Author;
import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.BookForm;
import me.vincentlin.bookstore.model.Genre;
import me.vincentlin.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired GenreRepository genreRepository;
    @Autowired AuthorRepository authorRepository;
    @Autowired BookRepository bookRepository;

    @Override public Book parse(BookForm form) {
        Book book = new Book();
        book.setId(form.getId());
        book.setDescription(form.getDescription());
        book.setInventory(form.getInventory());
        book.setIsbn(form.getIsbn());
        book.setImage(form.getImage());
        book.setName(form.getName());
        book.setPrice(form.getPrice());

        Genre genre = null;
        if (form.getGenre() != null && (genre = genreRepository.getOne(form.getGenre())) == null) {
            return null;
        }

        List<Author> authors = new ArrayList<>();
        if(form.getAuthors() != null) {
            for(Long id: form.getAuthors()) {
                Author author = authorRepository.getOne(id);
                if(author == null)
                    return null;
                authors.add(author);
            }
        }
        book.setGenre(genre);
        book.setAuthors(authors);

        bookRepository.save(book);
        return book;

    }
}
