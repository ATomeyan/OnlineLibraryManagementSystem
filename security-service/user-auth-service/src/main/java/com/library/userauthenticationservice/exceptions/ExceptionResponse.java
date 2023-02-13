package com.library.userauthenticationservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Artur Tomeyan
 * @date 13/02/2023
 */
@Data
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private LocalDateTime dateTime;
    private int statusCode;
    private String error;
    private String message;
}