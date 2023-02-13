package com.library.userservice.exceptions;

/**
 * @author Artur Tomeyan
 * @date 10/02/2023
 */
public class ObjectDoesntExistException extends RuntimeException {

    public ObjectDoesntExistException() {
    }

    public ObjectDoesntExistException(String message) {
        super(message);
    }
}