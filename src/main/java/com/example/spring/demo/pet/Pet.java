package com.example.spring.demo.pet;

import com.example.spring.demo.group.Group;
import com.example.spring.demo.person.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@NoArgsConstructor
@Table
public class Pet {

    @Id
    long id;

    @NotEmpty
    String name;

    @NotEmpty
    String type;

    int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_pid")
    private Person owner;

    @OneToOne
    @JoinColumn(name = "favorite_in_group", referencedColumnName = "id")
    private Group group;

}
