package me.vincentlin.bookstore.model.projection;

import me.vincentlin.bookstore.model.Author;
import me.vincentlin.bookstore.model.Book;
import me.vincentlin.bookstore.model.Genre;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Vincent on 2017/4/4.
 */
@Projection(types = {Genre.class, Book.class, Author.class})
public interface NameExcerpt {
    String getName();
}

