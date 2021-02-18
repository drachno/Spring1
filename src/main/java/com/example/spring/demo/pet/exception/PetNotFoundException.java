package com.example.spring.demo.pet.exception;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(long id) {
        super("Can't find pet with this id: " + id);
    }
}
