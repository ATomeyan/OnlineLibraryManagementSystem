package com.library.service.impl;

import ch.qos.logback.classic.Logger;
import com.library.dto.author.AuthorRequestDto;
import com.library.dto.book.BookRequestDto;
import com.library.dto.book.BookResponseDto;
import com.library.dto.book.BookUpdateRequestDto;
import com.library.dto.genre.GenreRequestDto;
import com.library.dto.publisher.PublisherRequestDto;
import com.library.entity.Book;
import com.library.exceptions.AlreadyExistsException;
import com.library.exceptions.EmptyException;
import com.library.exceptions.NotFoundException;
import com.library.exceptions.NotValidException;
import com.library.mapper.BookMapper;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Artur Tomeyan
 * @date 22/11/2022
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public List<BookResponseDto> getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

        Page<Book> bookDtoList = bookRepository.findAll(pageable);

        if (bookDtoList.isEmpty()) {
            LOGGER.error("The list of books is empty.");
            throw new EmptyException("The list of books is empty.");
        }

        if (bookDtoList.stream().iterator().next().getAuthorsBooks().iterator().next().getAuthor() == null) {
            LOGGER.error("The list of authors is empty.");
            throw new EmptyException("The list of authors is empty.");
        }

        if (bookDtoList.stream().iterator().next().getBooksPublishers().iterator().next().getPublisher() == null) {
            LOGGER.error("The list of publishers is empty.");
            throw new EmptyException("The list of publishers is empty.");
        }

        return bookDtoList.stream().map(bookMapper::mapEntityToAllBookResponseDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookResponseDto getByBookId(UUID id) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Book> bookById = bookRepository.findById(id);

        if (bookById.isEmpty()) {
            LOGGER.error("The book by id is not found.");
            throw new NotFoundException("The book by id is not found.");
        }

        return bookMapper.mapEntityToResponseDto(bookById.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {

        if (bookRequestDto == null) {
            LOGGER.error("Book info object can't be null.");
            throw new NotValidException("Book info object can't be null.");
        }

        List<Book> bookByCriteria = bookRepository.findByCriteria(bookRequestDto.getTitle()).orElse(null);

        if (bookByCriteria != null && !bookByCriteria.isEmpty()) {
            for (Book b : bookByCriteria) {
                if (Objects.equals(bookRequestDto.getEdition(), b.getEdition())) {
                    LOGGER.error("The book with given name already exists.");
                    throw new AlreadyExistsException("The book with given name already exists.");
                }
            }
        }

        Book book = bookMapper.mapRequestDtoToEntity(bookRequestDto);
        Book createdBook = bookRepository.save(book);

        LOGGER.info("Book is successfully saved.");

        return bookMapper.mapEntityToResponseDto(createdBook);
    }

    @Override
    @Transactional
    public BookResponseDto updateBook(UUID id, BookUpdateRequestDto bookUpdateRequestDto) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            LOGGER.error("The book by id is not found.");
            throw new NotFoundException("The book by id is not found.");
        }

        Book updatedBook = bookRepository.save(bookMapper.mapUpdateDtoToEntity(bookUpdateRequestDto));
        LOGGER.info("Book successfully updated.");
        return bookMapper.mapEntityToResponseDto(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(UUID id) {
        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            LOGGER.error("The book by id is not found.");
            throw new NotFoundException("The book by id is not found.");
        }

        book.ifPresent(bookRepository::delete);
        LOGGER.info("Book is deleted.");
    }

    @Override
    @Transactional
    public List<BookResponseDto> getBookByTitle(String title) {

        if (title == null) {
            LOGGER.error("The book title can't be null.");
            throw new NotValidException("The book title can't be null.");
        }

        List<Book> bookByTitle = bookRepository.findBookByTitle(title);

        if (bookByTitle.isEmpty()) {
            LOGGER.error("The book by title wasn't found.");
            throw new NotFoundException("The book by title wasn't found.");
        }

        return bookByTitle.stream().map(bookMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BookResponseDto> getBooksByPublishYear(Integer year) {

        if (year == null || year <= 0) {
            LOGGER.error("Year data can't be null, less or equal to 0.");
            throw new NotValidException("Year data can't be null, less or equal to 0.");
        }

        List<Book> booksByPublishYear = bookRepository.findBooksByPublishYear(year);

        if (booksByPublishYear.isEmpty()) {
            LOGGER.error("Book by publish year isn't found.");
            throw new NotFoundException("Book by publish year isn't found.");
        }

        return booksByPublishYear.stream().map(bookMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BookResponseDto> getBooksByAuthor(AuthorRequestDto authorRequestDto) {

        if (authorRequestDto == null) {
            LOGGER.error("The author data is not valid.");
            throw new NotValidException("The author data is not valid.");
        }

        String firstName = authorRequestDto.getFirstName();
        if (firstName == null) {
            LOGGER.error("Author first name is null.");
            throw new NotValidException("Author first name is null.");
        }
        String lastName = authorRequestDto.getLastName();
        if (lastName == null) {
            LOGGER.error("Author last name is null.");
            throw new NotValidException("Author last name is null.");
        }

        List<Book> booksByAuthorFirstNameAndLastName = bookRepository.findBooksByAuthorFirstNameAndLastName(firstName, lastName);

        if (booksByAuthorFirstNameAndLastName.isEmpty()) {
            LOGGER.error("Book by author is not found.");
            throw new NotFoundException("Book by author is not found.");
        }

        return booksByAuthorFirstNameAndLastName.stream().map(bookMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BookResponseDto> getBooksByPublisher(PublisherRequestDto publisherRequestDto) {

        if (publisherRequestDto == null) {
            LOGGER.error("Publisher data is not valid.");
            throw new NotValidException("Publisher data is not valid.");
        }

        String name = publisherRequestDto.getName();
        if (StringUtils.isBlank(name)) {
            LOGGER.error("Publisher name is empty.");
            throw new NotValidException("Publisher name is empty.");
        }

        List<Book> booksByPublisher = bookRepository.findBooksByPublisher(name);
        if (booksByPublisher.isEmpty()) {
            LOGGER.error("Book by publisher is not found.");
            throw new NotFoundException("Book by publisher is not found.");
        }

        return booksByPublisher.stream().map(bookMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BookResponseDto> getBooksByGenre(GenreRequestDto genreRequestDto) {
        if (genreRequestDto == null) {
            LOGGER.error("Publisher data is not valid.");
            throw new NotValidException("Publisher data is not valid.");
        }

        String name = genreRequestDto.getName();
        if (StringUtils.isBlank(name)) {
            LOGGER.error("Genre name is empty.");
            throw new NotValidException("Genre name is empty.");
        }

        List<Book> booksByGenre = bookRepository.findBooksByGenre(name);

        if (booksByGenre.isEmpty()) {
            LOGGER.error("Book by genre is not found.");
            throw new NotFoundException("Book by genre is not found.");
        }

        return booksByGenre.stream().map(bookMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }
}