package com.library.mapper;

import com.library.dto.author.AuthorRequestDto;
import com.library.dto.author.AuthorResponseDto;
import com.library.dto.author.AuthorUpdateRequestDto;
import com.library.entity.Author;
import org.springframework.stereotype.Component;

/**
 * @author Artur Tomeyan
 * @date 28/11/2022
 */
@Component
public final class AuthorMapper {

    private AuthorMapper() {
    }

    public AuthorResponseDto mapEntityToResponseDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        setAbstractFieldsFromEntityToResponseDto(author, authorResponseDto);

        return authorResponseDto;
    }

    public Author mapRequestDtoToEntity(AuthorRequestDto authorRequestDto) {
        Author author = new Author();

        setAbstractFieldsFromRequestDtoToEntity(authorRequestDto, author);

        return author;
    }

    public Author mapUpdateDtoToEntity(AuthorUpdateRequestDto authorUpdateRequestDto) {
        Author author = new Author();

        author.setId(authorUpdateRequestDto.getId());
        author.setFirstName(authorUpdateRequestDto.getFirstName());
        author.setLastName(authorUpdateRequestDto.getLastName());

        return author;
    }

    private void setAbstractFieldsFromEntityToResponseDto(Author author, AuthorResponseDto authorResponseDto) {
        authorResponseDto.setId(author.getId());
        authorResponseDto.setFirstName(author.getFirstName());
        authorResponseDto.setLastName(author.getLastName());
    }

    private void setAbstractFieldsFromRequestDtoToEntity(AuthorRequestDto authorRequestDto, Author author) {
        author.setId(authorRequestDto.getId());
        author.setFirstName(authorRequestDto.getFirstName());
        author.setLastName(authorRequestDto.getLastName());
    }
}