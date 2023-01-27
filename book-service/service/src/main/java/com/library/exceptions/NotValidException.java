package com.library.exceptions;

/**
 * @author Artur Tomeyan
 * @date 15/12/2022
 */
public class NotValidException extends RuntimeException {

    public NotValidException() {
        super();
    }

    public NotValidException(String message) {
        super(message);
    }
}