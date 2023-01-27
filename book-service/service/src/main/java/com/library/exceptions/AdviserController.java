package com.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Artur Tomeyan
 * @date 01/12/2022
 */
@ControllerAdvice
public class AdviserController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyException.class)
    @ResponseBody
    public ResponseEntity<Object> handleObjectEmptyException(EmptyException exception) {

        return new ResponseEntity<>(
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.NO_CONTENT.value(),
                        HttpStatus.NO_CONTENT.getReasonPhrase(),
                        exception.getMessage()),
                HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFoundException(NotFoundException exception) {

        return new ResponseEntity<>(
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleObjectAlreadyExistException(AlreadyExistsException exception) {

        return new ResponseEntity<>(
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<Object> handleObjectNotValidException(NotValidException exception) {

        return new ResponseEntity<>(
                new ExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}