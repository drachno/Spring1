package com.example.spring.demo.person;

import com.example.spring.demo.person.exception.PersonNotFoundException;
import com.example.spring.demo.person.exception.PersonWIthThisPIDAlreadyExists;

import java.util.List;

public interface PersonService {

    Person getById(long pid) throws PersonNotFoundException;

    void deleteById(long pid) throws PersonNotFoundException;

    List<Person> getAll();

    void addPerson(Person person) throws PersonWIthThisPIDAlreadyExists;

    void updatePerson(Person person, long pid) throws PersonNotFoundException;
}
