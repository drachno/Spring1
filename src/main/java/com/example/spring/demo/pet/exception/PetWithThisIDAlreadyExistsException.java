package com.example.spring.demo.pet.exception;

public class PetWithThisIDAlreadyExistsException extends RuntimeException {

    public PetWithThisIDAlreadyExistsException(long id) {
        super("Pet with this ID already exists " + id);
    }
}
