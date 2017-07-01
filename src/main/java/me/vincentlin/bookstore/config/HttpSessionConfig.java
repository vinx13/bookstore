package me.vincentlin.bookstore;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by Vincent on 2017/4/2.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

}