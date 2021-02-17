package com.example.spring.demo.person.exception;

public class PersonWIthThisPIDAlreadyExists extends RuntimeException {

    public PersonWIthThisPIDAlreadyExists(long pid) {
        super("Person with this ID already exists " + pid);
    }
}

