package me.vincentlin.bookstore.dao;

import me.vincentlin.bookstore.model.Genre;
import me.vincentlin.bookstore.model.projection.NameExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Vincent on 2017/4/1.
 */
@RepositoryRestResource(excerptProjection = NameExcerpt.class)
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
