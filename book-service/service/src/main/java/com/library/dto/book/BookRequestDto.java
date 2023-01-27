package com.library.dto.book;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 02/12/2022
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto extends AbstractBookRequestAndResponseDto implements Serializable {

    @NotNull
    @NotEmpty
    private List<UUID> authors;
    @NotNull
    @NotEmpty
    private List<UUID> publishers;
    @NotNull
    @NotEmpty
    private List<UUID> genres;
}