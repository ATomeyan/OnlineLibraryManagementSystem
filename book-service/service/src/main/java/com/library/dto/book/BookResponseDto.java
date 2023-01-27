package com.library.dto.book;

import com.library.dto.author.AuthorResponseDto;
import com.library.dto.genre.GenreResponseDto;
import com.library.dto.publisher.PublisherResponseDto;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Artur Tomeyan
 * @date 02/12/2022
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto extends AbstractBookRequestAndResponseDto implements Serializable {

    private List<AuthorResponseDto> authors;
    private List<PublisherResponseDto> publishers;
    private List<GenreResponseDto> genres;
}