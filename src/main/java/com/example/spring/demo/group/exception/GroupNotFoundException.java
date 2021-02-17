package com.example.spring.demo.group.exception;

public class GroupNotFoundException extends GroupException {

    public GroupNotFoundException(long id) {
        super("Can't find Group with: " + id);
    }
}
