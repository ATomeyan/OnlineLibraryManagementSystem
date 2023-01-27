package com.library.service.impl;

import ch.qos.logback.classic.Logger;
import com.library.dto.author.AuthorRequestDto;
import com.library.dto.author.AuthorResponseDto;
import com.library.dto.author.AuthorUpdateRequestDto;
import com.library.entity.Author;
import com.library.exceptions.EmptyException;
import com.library.exceptions.NotFoundException;
import com.library.exceptions.NotValidException;
import com.library.mapper.AuthorMapper;
import com.library.repository.AuthorRepository;
import com.library.service.AuthorService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Artur Tomeyan
 * @date 28/11/2022
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

        Page<Author> authorList = authorRepository.findAll(pageable);

        if (authorList.isEmpty()) {
            LOGGER.error("The list of authors is empty.");
            throw new EmptyException("The list of authors is empty.");
        }

        return authorList.stream().map(authorMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorResponseDto> getAuthorById(UUID id) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Author> authorById = authorRepository.findById(id);

        if (authorById.isEmpty()) {
            LOGGER.error("The author by id is not found.");
            throw new NotFoundException("The author by id is not found.");
        }

        AuthorResponseDto authorResponseDto = authorMapper.mapEntityToResponseDto(authorById.get());

        return Optional.of(authorResponseDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {

        if (authorRequestDto == null) {
            LOGGER.error("Author info object data can't be null.");
            throw new NotValidException("Author info object data can't be null.");
        }

        Author author = authorMapper.mapRequestDtoToEntity(authorRequestDto);

        Author createdAuthor = authorRepository.save(author);

        LOGGER.info("Author is successfully saved.");

        return authorMapper.mapEntityToResponseDto(createdAuthor);
    }

    @Override
    @Transactional
    public AuthorResponseDto updateAuthor(UUID id, AuthorUpdateRequestDto authorUpdateRequestDto) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            LOGGER.error("The author by id is not found.");
            throw new NotFoundException("The author by id is not found.");
        }

        Author updatedAuthor = authorRepository.save(authorMapper.mapUpdateDtoToEntity(authorUpdateRequestDto));
        LOGGER.info("Author successfully updated.");

        return authorMapper.mapEntityToResponseDto(updatedAuthor);
    }

    @Override
    @Transactional
    public void deleteAuthor(UUID id) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            LOGGER.error("The author by id is not found.");
            throw new NotFoundException("The author by id is not found.");
        }

        author.ifPresent(authorRepository::delete);
        LOGGER.info("Author is deleted");
    }

    @Override
    public AuthorResponseDto getAuthorByName(AuthorRequestDto authorRequestDto) {

        if (authorRequestDto == null) {
            LOGGER.error("Requested author is null.");
            throw new NotValidException("Requested author is null.");
        }

        String firstName = authorRequestDto.getFirstName();
        String lastName = authorRequestDto.getLastName();

        Optional<Author> authorByFirstNameAndLastName = authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
        if (authorByFirstNameAndLastName.isEmpty()) {
            LOGGER.error("Author by first and last name not found.");
            throw new NotFoundException("Author by first and last name not found.");
        }

        return authorMapper.mapEntityToResponseDto(authorByFirstNameAndLastName.get());
    }
}