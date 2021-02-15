package com.example.spring.demo.person.exception;

public class PersonWIthThisPIDAlreadyExists extends Exception {

    public PersonWIthThisPIDAlreadyExists(long pid) {
        super("Person with this ID already exists " + pid);
    }
}

