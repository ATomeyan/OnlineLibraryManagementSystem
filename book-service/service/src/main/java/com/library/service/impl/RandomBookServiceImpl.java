package com.library.service.impl;

import ch.qos.logback.classic.Logger;
import com.library.dto.book.BookResponseDto;
import com.library.entity.Book;
import com.library.mapper.BookMapper;
import com.library.repository.BookRepository;
import com.library.service.RandomBookService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Artur Tomeyan
 * @date 23.05.2023
 */
@Service
public class RandomBookServiceImpl implements RandomBookService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(RandomBookService.class);
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public RandomBookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto bookResponseDto() {
        List<Book> books = bookRepository.findAll();

        List<BookResponseDto> responseBooks = books.stream().map(bookMapper::mapEntityToAllBookResponseDto).collect(Collectors.toList());

        Random random = new Random();

        int i = random.nextInt(responseBooks.size());

        return responseBooks.get(i);
    }
}