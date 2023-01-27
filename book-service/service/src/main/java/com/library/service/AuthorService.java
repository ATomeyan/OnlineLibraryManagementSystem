package com.library.service;

import com.library.dto.author.AuthorRequestDto;
import com.library.dto.author.AuthorResponseDto;
import com.library.dto.author.AuthorUpdateRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 28/11/2022
 */
public interface AuthorService {
    List<AuthorResponseDto> getAllAuthors(Integer pageNo, Integer pageSize, String sortBy);

    Optional<AuthorResponseDto> getAuthorById(UUID id);

    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);

    AuthorResponseDto updateAuthor(UUID id, AuthorUpdateRequestDto authorUpdateRequestDto);

    void deleteAuthor(UUID id);

    AuthorResponseDto getAuthorByName(AuthorRequestDto authorRequestDto);
}