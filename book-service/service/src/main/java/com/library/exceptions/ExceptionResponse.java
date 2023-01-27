package com.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Artur Tomeyan
 * @date 13/12/2022
 */
@Data
@AllArgsConstructor
@ToString
public class ExceptionResponse {

    private LocalDateTime dateTime;
    private int httpStatusCode;
    private String error;
    private String message;
}