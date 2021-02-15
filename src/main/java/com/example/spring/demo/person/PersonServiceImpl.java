package com.example.spring.demo.person;

import com.example.spring.demo.person.exception.PersonNotFoundException;
import com.example.spring.demo.person.exception.PersonWIthThisPIDAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public void addPerson(Person person) throws PersonWIthThisPIDAlreadyExists {
        personRepository.save(person);
    }

    @Override
    public Person getById(long pid) throws PersonNotFoundException {
        Person person = personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
        return person;
    }

    @Override
    public void deleteById(long pid) throws PersonNotFoundException {
        if (personRepository.existsById(pid)) {
            personRepository.deleteById(pid);
        } else {
            throw new PersonNotFoundException(pid);
        }
    }

    @Override
    public void updatePerson(Person newPerson, long pid) throws PersonNotFoundException {
        personRepository.findById(pid).map(person -> {
            person.setName(newPerson.getName());
            person.setSurname(newPerson.getSurname());
            person.setMiddleNAme(newPerson.getMiddleNAme());
            person.setEmail(newPerson.getEmail());
            person.setPhone(newPerson.getPhone());
            return personRepository.save(person);
        }).orElseThrow(() -> new PersonNotFoundException(pid));
    }
}
