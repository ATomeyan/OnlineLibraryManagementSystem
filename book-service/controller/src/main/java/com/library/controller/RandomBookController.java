package com.library.controller;

import com.library.dto.book.BookResponseDto;
import com.library.service.RandomBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artur Tomeyan
 * @date 23.05.2023
 */
@RestController
@RequestMapping("/api/v1/random")
public class RandomBookController {

    private final RandomBookService randomBookService;

    public RandomBookController(RandomBookService randomBookService) {
        this.randomBookService = randomBookService;
    }

    @GetMapping
    public ResponseEntity<BookResponseDto> randomBookResponse() {

        BookResponseDto bookResponseDto = randomBookService.bookResponseDto();
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
}