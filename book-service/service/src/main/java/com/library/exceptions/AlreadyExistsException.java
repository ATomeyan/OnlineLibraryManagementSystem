package com.library.exceptions;

/**
 * @author Artur Tomeyan
 * @date 15/12/2022
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}