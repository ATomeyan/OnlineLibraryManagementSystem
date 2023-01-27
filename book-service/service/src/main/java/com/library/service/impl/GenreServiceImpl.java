package com.library.service.impl;

import ch.qos.logback.classic.Logger;
import com.library.dto.genre.GenreRequestDto;
import com.library.dto.genre.GenreResponseDto;
import com.library.dto.genre.GenreUpdateRequestDto;
import com.library.entity.Genre;
import com.library.exceptions.EmptyException;
import com.library.exceptions.NotFoundException;
import com.library.exceptions.NotValidException;
import com.library.mapper.GenreMapper;
import com.library.repository.GenreRepository;
import com.library.service.GenreService;
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
 * @date 08/12/2022
 */
@Service
public class GenreServiceImpl implements GenreService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(GenreService.class);
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreResponseDto> getAllGenres(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        Page<Genre> genres = genreRepository.findAll(pageable);

        if (genres.isEmpty()) {
            LOGGER.error("The list of genre is empty.");
            throw new EmptyException("The list of genre is empty.");
        }

        return genres.stream().map(genreMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public Optional<GenreResponseDto> getGenreById(UUID id) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Genre> genreById = genreRepository.findById(id);

        if (genreById.isEmpty()) {
            LOGGER.error("The genre by id is not found.");
            throw new NotFoundException("The genre by id is not found.");
        }

        GenreResponseDto genreResponseDto = genreMapper.mapEntityToResponseDto(genreById.get());

        return Optional.of(genreResponseDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GenreResponseDto createGenre(GenreRequestDto genreRequestDto) {

        if (genreRequestDto == null) {
            LOGGER.error("Genre info object can't be null.");
            throw new NotValidException("Genre info object can't be null.");
        }

        Genre genre = genreMapper.mapRequestDtoToEntity(genreRequestDto);

        Genre createdGenre = genreRepository.save(genre);
        LOGGER.info("Genre is successfully saved.");

        return genreMapper.mapEntityToResponseDto(createdGenre);
    }

    @Override
    @Transactional
    public GenreResponseDto updateGenre(UUID id, GenreUpdateRequestDto genreUpdateRequestDto) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Genre genre = genreRepository.findById(id).orElse(null);

        if (genre == null) {
            LOGGER.error("The genre by id is not found.");
            throw new NotFoundException("The genre by id is not found.");
        }

        Genre updatedGenre = genreRepository.save(genreMapper.mapUpdateRequestDtoToEntity(genreUpdateRequestDto));
        LOGGER.info("Genre is successfully updated.");

        return genreMapper.mapEntityToResponseDto(updatedGenre);
    }

    @Override
    @Transactional
    public void deleteGenre(UUID id) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Genre> genre = genreRepository.findById(id);

        if (genre.isEmpty()) {
            LOGGER.error("The genre by id is not found.");
            throw new NotFoundException("The genre by id is not found.");
        }

        genre.ifPresent(genreRepository::delete);
        LOGGER.info("Genre is deleted successfully");
    }

    @Override
    public GenreResponseDto getGenreByName(GenreRequestDto genreRequestDto) {

        if (genreRequestDto == null) {
            LOGGER.error("Genre can't be null.");
            throw new NotValidException("Genre can't be null.");
        }

        String name = genreRequestDto.getName();

        Optional<Genre> genreByName = genreRepository.findByName(name);
        if (genreByName.isEmpty()) {
            LOGGER.error("Genre by name is not found.");
            throw new NotFoundException("Genre by name is not found.");
        }

        return genreMapper.mapEntityToResponseDto(genreByName.get());
    }
}