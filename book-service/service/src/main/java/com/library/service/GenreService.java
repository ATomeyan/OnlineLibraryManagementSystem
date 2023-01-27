package com.library.service;

import com.library.dto.genre.GenreRequestDto;
import com.library.dto.genre.GenreResponseDto;
import com.library.dto.genre.GenreUpdateRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 08/12/2022
 */
public interface GenreService {
    List<GenreResponseDto> getAllGenres(Integer pageNo, Integer pageSize, String sortBy);

    Optional<GenreResponseDto> getGenreById(UUID id);

    GenreResponseDto createGenre(GenreRequestDto genreRequestDto);

    GenreResponseDto updateGenre(UUID id, GenreUpdateRequestDto genreUpdateRequestDto);

    void deleteGenre(UUID id);

    GenreResponseDto getGenreByName(GenreRequestDto genreRequestDto);
}