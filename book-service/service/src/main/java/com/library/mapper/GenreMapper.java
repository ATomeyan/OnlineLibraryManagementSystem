package com.library.mapper;

import com.library.dto.genre.GenreRequestDto;
import com.library.dto.genre.GenreResponseDto;
import com.library.dto.genre.GenreUpdateRequestDto;
import com.library.entity.Genre;
import org.springframework.stereotype.Component;

/**
 * @author Artur Tomeyan
 * @date 08/12/2022
 */
@Component
public final class GenreMapper {

    private GenreMapper() {
    }

    public GenreResponseDto mapEntityToResponseDto(Genre genre) {
        GenreResponseDto genreResponseDto = new GenreResponseDto();

        genreResponseDto.setId(genre.getId());
        genreResponseDto.setName(genre.getName());

        return genreResponseDto;
    }

    public Genre mapRequestDtoToEntity(GenreRequestDto genreRequestDto) {
        Genre genre = new Genre();

        genre.setId(genreRequestDto.getId());
        genre.setName(genreRequestDto.getName());

        return genre;
    }

    public Genre mapUpdateRequestDtoToEntity(GenreUpdateRequestDto genreUpdateRequestDto) {
        Genre genre = new Genre();

        genre.setId(genreUpdateRequestDto.getId());
        genre.setName(genreUpdateRequestDto.getName());

        return genre;
    }
}