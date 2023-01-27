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
public class AuthorRequestDto implements Serializable {
    protected UUID id;
    @NotEmpty
    protected String firstName;
    @NotEmpty
    protected String lastName;
}