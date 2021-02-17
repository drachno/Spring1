package com.example.spring.demo.group.exception;

public class GroupExistsException extends GroupException {

    public GroupExistsException(String message) {
        super(message);
    }
}
