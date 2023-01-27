package com.library.dto.publisher;

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
public class PublisherRequestDto implements Serializable {

    private UUID id;
    @NotEmpty
    private String name;
}