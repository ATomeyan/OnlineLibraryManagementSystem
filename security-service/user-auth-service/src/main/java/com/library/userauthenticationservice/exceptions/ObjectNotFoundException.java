package com.library.userauthenticationservice.exceptions;

/**
 * @author Artur Tomeyan
 * @date 13/02/2023
 */
public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}