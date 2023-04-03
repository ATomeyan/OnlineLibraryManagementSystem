package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dto.book.BookRequestDto;
import com.library.entity.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Artur Tomeyan
 * @date 31.03.2023
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getBooks() throws Exception {

        Book book = Book.builder()
                .title("Effective Java")
                .page(413)
                .publishYear(2018)
                .edition("3th edition")
                .description("About java best practises")
                .build();

        given(bookService.createBook(any(BookRequestDto.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions resultActions = mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.page", is(book.getPage())))
                .andExpect(jsonPath("$.publishYear", is(book.getPublishYear())))
                .andExpect(jsonPath("$.edition", is(book.getEdition())))
                .andExpect(jsonPath("$.description", is(book.getDescription())));
    }
}