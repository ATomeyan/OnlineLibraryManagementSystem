package com.library.dto.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 25/11/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreRequestDto implements Serializable {

    private UUID id;
    @NotEmpty
    private String name;
}