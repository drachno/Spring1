package com.example.spring.demo.group;

import com.example.spring.demo.person.Person;
import com.example.spring.demo.pet.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"group\"")
public class Group {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @SequenceGenerator(name = "group_generator", sequenceName = "group_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_generator")
    private long id;
    /**
     * The name.
     */
    private @NonNull String name;
    /**
     * The details.
     */
    private String details;
    /**
     * The persons.
     */
    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Person> persons;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "group")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Pet pet;
}
