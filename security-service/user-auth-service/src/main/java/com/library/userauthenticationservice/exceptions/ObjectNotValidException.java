package com.library.userauthenticationservice.exceptions;

/**
 * @author Artur Tomeyan
 * @date 13/02/2023
 */
public class ObjectNotValidException extends RuntimeException{

    public ObjectNotValidException() {
    }

    public ObjectNotValidException(String message) {
        super(message);
    }
}