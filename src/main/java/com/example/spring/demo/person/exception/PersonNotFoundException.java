package com.example.spring.demo.person.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(long pid) {
        super("Can't find person with " + pid);
    }
}
