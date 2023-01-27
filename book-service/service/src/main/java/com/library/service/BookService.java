package com.library.service;

import com.library.dto.author.AuthorRequestDto;
import com.library.dto.book.BookRequestDto;
import com.library.dto.book.BookResponseDto;
import com.library.dto.book.BookUpdateRequestDto;
import com.library.dto.genre.GenreRequestDto;
import com.library.dto.publisher.PublisherRequestDto;

import java.util.List;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 22/11/2022
 */
public interface BookService {

    List<BookResponseDto> getAllBooks(Integer pageNo, Integer pageSize, String sortBy);

    BookResponseDto getByBookId(UUID id);

    BookResponseDto createBook(BookRequestDto bookDto);

    BookResponseDto updateBook(UUID id, BookUpdateRequestDto bookUpdateRequestDto);

    void deleteBook(UUID id);

    List<BookResponseDto> getBookByTitle(String title);

    List<BookResponseDto> getBooksByPublishYear(Integer year);

    List<BookResponseDto> getBooksByAuthor(AuthorRequestDto authorRequestDto);

    List<BookResponseDto> getBooksByPublisher(PublisherRequestDto publisherRequestDto);

    List<BookResponseDto> getBooksByGenre(GenreRequestDto genreRequestDto);
}