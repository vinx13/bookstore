package me.vincentlin.bookstore.dao;

import java.util.List;
import me.vincentlin.bookstore.model.Author;
import me.vincentlin.bookstore.model.projection.NameExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Vincent on 2017/4/1.
 */
@RepositoryRestResource(excerptProjection = NameExcerpt.class)
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameStartsWith(@Param("name") String name);
}
