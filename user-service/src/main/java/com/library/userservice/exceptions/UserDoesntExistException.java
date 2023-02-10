package com.library.userservice.exceptions;

/**
 * @author Artur Tomeyan
 * @date 10/02/2023
 */
public class UserDoesntExistException extends RuntimeException {

    public UserDoesntExistException() {
    }

    public UserDoesntExistException(String message) {
        super(message);
    }
}