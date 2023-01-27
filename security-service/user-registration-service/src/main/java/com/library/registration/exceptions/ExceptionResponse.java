package com.library.registration.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Artur Tomeyan
 * @date 25/01/2023
 */
@Data
@AllArgsConstructor
@ToString
public class ExceptionResponse {

    private final LocalDateTime dateTime;
    private final int httpCode;
    private final String errorResponse;
    private final String message;
}