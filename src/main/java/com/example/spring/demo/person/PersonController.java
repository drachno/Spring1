package com.example.spring.demo.person;

import com.example.spring.demo.person.exception.PersonNotFoundException;
import com.example.spring.demo.person.exception.PersonWIthThisPIDAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> list = personService.getAll();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/{pid}", produces = "application/json")
    public ResponseEntity<Person> getPersonByPid(@PathVariable("pid") Long pid) {
        try {
            Person person = personService.getById(pid);
            return new ResponseEntity<Person>(person, HttpStatus.OK);
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> addPerson(@RequestBody Person person) throws PersonWIthThisPIDAlreadyExists {
        try {
            personService.addPerson(person);
            return ResponseEntity.ok().build();
        } catch (PersonWIthThisPIDAlreadyExists e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<Void> deletePerson(@PathVariable("pid") Long pid) throws PersonNotFoundException {
        try {
            personService.deleteById(pid);
            return ResponseEntity.ok().build();
        } catch (PersonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{pid}", consumes = "application/json")
    public ResponseEntity<Void> updatePerson(@RequestBody Person person, @PathVariable("pid") long pid) throws PersonNotFoundException {
        try {
            personService.updatePerson(person, pid);
            return ResponseEntity.ok().build();
        } catch (PersonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
