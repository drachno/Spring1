package com.example.spring.demo.pet;

import com.example.spring.demo.pet.exception.PetNotFoundException;
import com.example.spring.demo.pet.exception.PetWithThisIDAlreadyExistsException;

import java.util.List;

public interface PetService {

    Pet getById(long id) throws PetNotFoundException;

    void deleteById(long id) throws PetNotFoundException;

    List<Pet> getAll();

    void addPet(Pet pet) throws PetWithThisIDAlreadyExistsException;

    void updatePet(Pet pet, long id) throws PetNotFoundException;

    void saveAndFlush(Pet pet);
}
