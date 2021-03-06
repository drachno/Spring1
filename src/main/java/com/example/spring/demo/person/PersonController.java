package com.example.spring.demo.person;

import com.example.spring.demo.group.Group;
import com.example.spring.demo.group.GroupService;
import com.example.spring.demo.group.exception.GroupNotFoundException;
import com.example.spring.demo.person.exception.PersonNotFoundException;
import com.example.spring.demo.pet.Pet;
import com.example.spring.demo.pet.PetService;
import com.example.spring.demo.pet.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    PersonService personService;
    GroupService groupService;
    PetService petService;

    @Autowired
    public PersonController(PersonService personService, GroupService groupService, PetService petService) {
        this.personService = personService;
        this.groupService = groupService;
        this.petService = petService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> list = personService.getAll();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/{pid}", produces = "application/json")
    public ResponseEntity<Person> getPersonByPid(@PathVariable("pid") Long pid) {
        Person person = personService.getById(pid);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<Void> deletePerson(@PathVariable("pid") Long pid) {
        personService.deleteById(pid);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{pid}", consumes = "application/json")
    public ResponseEntity<Void> updatePerson(@RequestBody Person person, @PathVariable("pid") long pid) {
        personService.updatePerson(person, pid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{pid}/groups")
    public ResponseEntity<Collection<Group>> getPersonGroups(@PathVariable("pid") Long pid) {

        try {
            Person person = personService.getById(pid);
            return new ResponseEntity<Collection<Group>>(person.getGroups(), HttpStatus.OK);
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("{pid}/groups/{id}")
    public ResponseEntity<?> setGroup(@PathVariable("pid") long pid, @PathVariable("id") long id) {

        try {
            Person person = personService.getById(pid);
            Group group = groupService.findById(id);
            Set<Group> groups = person.getGroups();
            groups.add(group);
            person.setGroups(groups);
            personService.saveAndFlush(person);
            return ResponseEntity.ok().build();
        } catch (GroupNotFoundException | PersonNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{pid}/groups/{id}")
    public ResponseEntity<?> removeFromGroup(@PathVariable("pid") long pid, @PathVariable("id") long id) {
        try {
            Person person = personService.getById(pid);
            Group group = groupService.findById(id);
            Set<Group> groups = person.getGroups();
            if (groups.contains(group)) {
                groups.remove(group);
                person.setGroups(groups);
                personService.saveAndFlush(person);
            }

            return ResponseEntity.ok().build();
        } catch (GroupNotFoundException | PersonNotFoundException ex) {
            //log.error("setGroup", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{pid}/pets")
    public ResponseEntity<Collection<Pet>> getPets(@PathVariable("pid") Long pid) {
        try {
            Person person = personService.getById(pid);
            return new ResponseEntity<Collection<Pet>>(person.getPets(), HttpStatus.OK);
        } catch (PetNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("{pid}/pets/{id}")
    public ResponseEntity<?> setPet(@PathVariable("pid") long pid, @PathVariable("id") long id) {

        try {
            Person person = personService.getById(pid);
            Pet pet = petService.getById(id);
            Set<Pet> pets = person.getPets();
            pets.add(pet);
            person.setPets(pets);
            personService.saveAndFlush(person);
            return ResponseEntity.ok().build();
        } catch (PetNotFoundException | PersonNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{pid}/pets/{id}")
    public ResponseEntity<?> removeFromPets(@PathVariable("pid") long pid, @PathVariable("id") long id) {
        try {
            Person person = personService.getById(pid);
            Pet pet = petService.getById(id);
            Set<Pet> pets = person.getPets();
            if (pets.contains(pet)) {
                pets.remove(pet);
                person.setPets(pets);
                personService.saveAndFlush(person);
            }
            return ResponseEntity.ok().build();
        } catch (PetNotFoundException | PersonNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
