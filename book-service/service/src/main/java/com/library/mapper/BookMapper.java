package com.library.mapper;

import com.library.dto.author.AuthorResponseDto;
import com.library.dto.book.AbstractBookRequestAndResponseDto;
import com.library.dto.book.BookRequestDto;
import com.library.dto.book.BookResponseDto;
import com.library.dto.book.BookUpdateRequestDto;
import com.library.dto.genre.GenreResponseDto;
import com.library.dto.publisher.PublisherResponseDto;
import com.library.entity.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Artur Tomeyan
 * @date 23/11/2022
 */
@Component
public final class BookMapper {

    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;
    private final PublisherMapper publisherMapper;

    private BookMapper(AuthorMapper authorMapper, GenreMapper genreMapper, PublisherMapper publisherMapper) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
        this.publisherMapper = publisherMapper;
    }

    public BookResponseDto mapEntityToResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        setAbstractFieldsFromEntityToResponseDto(bookResponseDto, book);

        List<Author> authors = book.getAuthorsBooks().stream().map(AuthorsBooks::getAuthor).collect(Collectors.toList());
        List<AuthorResponseDto> authorResponseDtoList = authors.stream().map(authorMapper::mapEntityToResponseDto).collect(Collectors.toList());

        List<Publisher> publishers = book.getBooksPublishers().stream().map(BooksPublishers::getPublisher).collect(Collectors.toList());
        List<PublisherResponseDto> publisherResponseDtoList = publishers.stream().map(publisherMapper::mapEntityToResponseDto).collect(Collectors.toList());

        List<Genre> genres = book.getGenresBooks().stream().map(GenresBooks::getGenre).collect(Collectors.toList());
        List<GenreResponseDto> genreResponseDtoList = genres.stream().map(genreMapper::mapEntityToResponseDto).collect(Collectors.toList());

        bookResponseDto.setAuthors(authorResponseDtoList);
        bookResponseDto.setPublishers(publisherResponseDtoList);
        bookResponseDto.setGenres(genreResponseDtoList);

        return bookResponseDto;
    }

    public BookResponseDto mapEntityToAllBookResponseDto(Book book){
        BookResponseDto bookResponseDto = new BookResponseDto();
        setAbstractFieldsFromEntityToResponseDto(bookResponseDto, book);
        return bookResponseDto;
    }

    public Book mapRequestDtoToEntity(BookRequestDto bookRequestDto) {
        Book book = new Book();
        setAbstractFieldsFromRequestDtoToEntity(book, bookRequestDto);

        List<UUID> authors = bookRequestDto.getAuthors();
        List<UUID> genres = bookRequestDto.getGenres();
        List<UUID> publishers = bookRequestDto.getPublishers();

        book.setAuthorsBooks(AuthorsBooksMapper.requestDtoToEntity(authors, book));
        book.setBooksPublishers(BooksPublishersMapper.requestDtoToEntity(publishers, book));
        book.setGenresBooks(GenresBooksMapper.requestDtoToEntity(genres, book));

        return book;
    }

    public Book mapUpdateDtoToEntity(BookUpdateRequestDto bookUpdateRequestDto) {

        Book book = new Book();

        Author author = authorMapper.mapUpdateDtoToEntity(bookUpdateRequestDto.getAuthors().stream().iterator().next());
        AuthorsBooks authorsBooks = new AuthorsBooks();

        authorsBooks.setAuthor(author);
        authorsBooks.setBook(book);

        Publisher publisher = publisherMapper.mapUpdateDtoToEntity(bookUpdateRequestDto.getPublishers().stream().iterator().next());
        BooksPublishers booksPublishers = new BooksPublishers();

        booksPublishers.setBook(book);
        booksPublishers.setPublisher(publisher);

        Genre genre = genreMapper.mapUpdateRequestDtoToEntity(bookUpdateRequestDto.getGenres().stream().iterator().next());
        GenresBooks genresBooks = new GenresBooks();

        genresBooks.setBook(book);
        genresBooks.setGenre(genre);

        book.setAuthorsBooks(Collections.singletonList(authorsBooks));
        book.setBooksPublishers(Collections.singletonList(booksPublishers));
        book.setGenresBooks(Collections.singletonList(genresBooks));

        setAbstractFieldsFromRequestDtoToEntity(book, bookUpdateRequestDto);

        return book;
    }

    private void setAbstractFieldsFromEntityToResponseDto(BookResponseDto bookResponseDto, Book book) {
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPage(book.getPage());
        bookResponseDto.setPublishYear(book.getPublishYear());
        bookResponseDto.setEdition(book.getEdition());
        bookResponseDto.setDescription(book.getDescription());
    }

    private void setAbstractFieldsFromRequestDtoToEntity(Book book, AbstractBookRequestAndResponseDto baseBookRequestDto) {
        book.setId(baseBookRequestDto.getId());
        book.setTitle(baseBookRequestDto.getTitle());
        book.setPage(baseBookRequestDto.getPage());
        book.setPublishYear(baseBookRequestDto.getPublishYear());
        book.setEdition(baseBookRequestDto.getEdition());
        book.setDescription(baseBookRequestDto.getDescription());
    }
}