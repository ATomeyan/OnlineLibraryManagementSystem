package com.library.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @author Artur Tomeyan
 * @date 20.04.2023
 */
@Profile("test")
@Configuration
public class BookServiceTestConfiguration {

    @Bean
    @Primary
    public BookService bookService(){
        return Mockito.mock(BookService.class);
    }
}