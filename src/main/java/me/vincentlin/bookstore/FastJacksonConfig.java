package me.vincentlin.bookstore;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;

/**
 * Created by Vincent on 2017/4/6.
 */
public class FastJacksonConfig {
    @Bean
    public Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
