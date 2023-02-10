package com.library.userservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Artur Tomeyan
 * @date 27/12/2022
 */
@ResponseStatus()
public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}