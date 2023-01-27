package com.library.dto.author;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 02/12/2022
 */
@Data
@NoArgsConstructor
public class AuthorUpdateRequestDto implements Serializable {

    @NotEmpty
    private UUID id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}