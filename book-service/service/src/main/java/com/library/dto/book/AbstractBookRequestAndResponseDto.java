package com.library.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 06/12/2022
 */
@Data
@NotNull
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractBookRequestAndResponseDto implements Serializable {

    protected UUID id;
    @NotEmpty
    protected String title;
    @NotNull
    protected Integer page;
    @NotNull
    protected Integer publishYear;
    @NotEmpty(message = "Book edition is empty or null.")
    protected String edition;
    protected byte[] image;
    @NotEmpty
    protected String description;
}