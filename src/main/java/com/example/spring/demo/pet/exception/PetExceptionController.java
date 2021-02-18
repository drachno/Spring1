package com.example.spring.demo.pet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetExceptionController {
    @ExceptionHandler(value = PetNotFoundException.class)
    public ResponseEntity<Object> exception(PetNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PetWithThisIDAlreadyExistsException.class)
    public ResponseEntity<Object> exception(PetWithThisIDAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
