package com.library.dto.book;

import com.library.dto.author.AuthorUpdateRequestDto;
import com.library.dto.genre.GenreUpdateRequestDto;
import com.library.dto.publisher.PublisherUpdateRequestDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
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
public class BookUpdateRequestDto extends AbstractBookRequestAndResponseDto implements Serializable {

    @NotEmpty
    private List<AuthorUpdateRequestDto> authors;
    @NotEmpty
    private List<PublisherUpdateRequestDto> publishers;
    @NotEmpty
    private List<GenreUpdateRequestDto> genres;
}