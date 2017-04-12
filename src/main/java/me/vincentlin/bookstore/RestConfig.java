package me.vincentlin.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 2017/4/9.
 */
@Configuration
public class RestConfig {

    /*
        Expose entity ids by default
     */
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer(EntityManagerFactory entityManagerFactory) {
        List<Class<?>> entityClasses = getAllManagedEntityTypes(entityManagerFactory);

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                for (Class<?> entityClass : entityClasses) {
                    config.exposeIdsFor(entityClass);
                }
                config
                        .setDefaultMediaType(MediaType.APPLICATION_JSON)
                        .setBasePath("api").useHalAsDefaultJsonMediaType(false);
            }
        };
    }

    private List<Class<?>> getAllManagedEntityTypes(EntityManagerFactory entityManagerFactory) {
        List<Class<?>> entityClasses = new ArrayList<>();
        Metamodel metamodel = entityManagerFactory.getMetamodel();
        for (ManagedType<?> managedType : metamodel.getManagedTypes()) {
            Class<?> javaType = managedType.getJavaType();
            if (javaType.isAnnotationPresent(Entity.class)) {
                entityClasses.add(managedType.getJavaType());
            }
        }
        return entityClasses;
    }

}