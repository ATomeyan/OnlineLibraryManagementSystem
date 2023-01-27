package com.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Artur Tomeyan
 * @date 01/12/2022
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyException extends RuntimeException {

    public EmptyException() {
        super();
    }

    public EmptyException(String message) {
        super(message);
    }
}