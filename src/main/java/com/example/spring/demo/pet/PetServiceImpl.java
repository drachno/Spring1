package com.example.spring.demo.pet;

import com.example.spring.demo.pet.exception.PetNotFoundException;
import com.example.spring.demo.pet.exception.PetWithThisIDAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public Pet getById(long id) throws PetNotFoundException {
        return petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
    }

    @Override
    public void deleteById(long pid) throws PetNotFoundException {

    }

    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public void addPet(Pet pet) throws PetWithThisIDAlreadyExistsException {
        if (petRepository.existsById(pet.getId())) {
            throw new PetWithThisIDAlreadyExistsException(pet.getId());
        }
        petRepository.save(pet);
    }


    @Override
    public void updatePet(Pet pet, long id) throws PetNotFoundException {
        if (petRepository.existsById(pet.getId())) {
            petRepository.save(pet);
        } else {
            throw new PetNotFoundException(pet.getId());
        }
    }

    @Override
    public void saveAndFlush(Pet pet) {
        petRepository.saveAndFlush(pet);
    }
}
