package com.baloise.m295.library.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS Configuration class for security
 * @author Julian Schiller
 */
@Configuration
@Profile("development")
public class DevConfiguration implements WebMvcConfigurer {

    /** {@inheritDoc} */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/library").addResourceLocations("classpath:/public/");
    }

    /** {@inheritDoc} */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PATCH", "DELETE")
            .allowedOrigins("*");
    }

}
