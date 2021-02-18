package com.example.spring.demo.pet;

import com.example.spring.demo.group.Group;
import com.example.spring.demo.group.GroupService;
import com.example.spring.demo.person.exception.PersonNotFoundException;
import com.example.spring.demo.pet.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pets/")
public class PetController {

    PetService petService;
    GroupService groupService;

    @Autowired
    public PetController(PetService petService, GroupService groupService) {
        this.petService = petService;
        this.groupService = groupService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Pet>> getPets() {
        List<Pet> list = petService.getAll();
        return new ResponseEntity<List<Pet>>(list, HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Long id) {
        Pet pet = petService.getById(id);
        return new ResponseEntity<Pet>(pet, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> addPet(@RequestBody Pet pet) {
        petService.addPet(pet);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePet(@PathVariable("id") Long id) {
        petService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "{id}", consumes = "application/json")
    public ResponseEntity<Void> updatePerson(@RequestBody Pet pet, @PathVariable("id") long id) {
        petService.updatePet(pet, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "{/group}", produces = "application/json")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Long id) {
        Group group = groupService.findById(id);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }

    @DeleteMapping("{petId}/{groupId}")
    public ResponseEntity<?> removeGroup(@PathVariable("petId") long petId, @PathVariable("groupId") long id) {
        try {
            Pet pet = petService.getById(petId);
            Group group = groupService.findById(id);
            if (pet.getGroup() == group) {
                pet.setGroup(null);
                petService.saveAndFlush(pet);
            }
            return ResponseEntity.ok().build();
        } catch (PetNotFoundException | PersonNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }


}
