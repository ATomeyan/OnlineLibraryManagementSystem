package com.library.userservice.exceptions;

/**
 * @author Artur Tomeyan
 * @date 25/01/2023
 */
public class NotValidException extends RuntimeException{

    public NotValidException() {
        super();
    }

    public NotValidException(String message) {
        super(message);
    }
}