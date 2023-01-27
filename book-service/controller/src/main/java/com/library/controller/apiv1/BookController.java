package com.library.controller.apiv1;

import com.library.dto.author.AuthorRequestDto;
import com.library.dto.book.BookRequestDto;
import com.library.dto.book.BookResponseDto;
import com.library.dto.book.BookUpdateRequestDto;
import com.library.dto.genre.GenreRequestDto;
import com.library.dto.publisher.PublisherRequestDto;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 22/11/2022
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "title") String sortBy) {
        List<BookResponseDto> allBooks = bookService.getAllBooks(pageNo, pageSize, sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable UUID id) {
        BookResponseDto byBookId = bookService.getByBookId(id);

        return ResponseEntity.status(HttpStatus.OK).body(byBookId);
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody @Valid BookRequestDto bookRequestDto) {
        BookResponseDto createBook = bookService.createBook(bookRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable UUID id, @RequestBody @Valid BookUpdateRequestDto bookUpdateRequestDto) {
        BookResponseDto updatedBook = bookService.updateBook(id, bookUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byTitle/{title}")
    public ResponseEntity<List<BookResponseDto>> getBookByTitle(@PathVariable String title) {
        List<BookResponseDto> byBookId = bookService.getBookByTitle(title);

        return ResponseEntity.status(HttpStatus.OK).body(byBookId);
    }

    @GetMapping("/byYear/{publishYear}")
    public ResponseEntity<List<BookResponseDto>> getBooksByPublishYear(@PathVariable Integer publishYear) {
        List<BookResponseDto> booksByPublishYear = bookService.getBooksByPublishYear(publishYear);

        return ResponseEntity.status(HttpStatus.OK).body(booksByPublishYear);
    }

    @PostMapping("/byAuthor")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto) {
        List<BookResponseDto> booksByAuthor = bookService.getBooksByAuthor(authorRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(booksByAuthor);
    }

    @PostMapping("/byPublisher")
    public ResponseEntity<List<BookResponseDto>> getBooksByPublisher(@RequestBody @Valid PublisherRequestDto publisherRequestDto) {
        List<BookResponseDto> booksByPublisher = bookService.getBooksByPublisher(publisherRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(booksByPublisher);
    }

    @PostMapping("/byGenre")
    public ResponseEntity<List<BookResponseDto>> getBooksByGenre(@RequestBody @Valid GenreRequestDto genreRequestDto) {
        List<BookResponseDto> booksByGenre = bookService.getBooksByGenre(genreRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(booksByGenre);
    }
}