package com.library.service.impl;

import com.library.dto.book.BookResponseDto;
import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Artur Tomeyan
 * @date 31.03.2023
 */
@SpringBootTest(classes = BookServiceImpl.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookServiceImplTests {

    @Autowired
    private BookService bookService;

    @Test
    public void whenBookIdIsProvided_thenRetrievedNameIsCorrect(){
        Mockito.when(bookService.getBookByTitle("Don Quixote"));

        List<BookResponseDto> donQuixote = bookService.getBookByTitle("Don Quixote");
        assertEquals("Don Quixote", donQuixote);
    }
}