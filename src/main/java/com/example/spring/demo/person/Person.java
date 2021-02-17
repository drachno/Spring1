package com.example.spring.demo.person;


import com.example.spring.demo.group.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table
public class Person {

    @Id
    @NotNull
    private long pid;

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @NotBlank
    @Column(name = "last_name")
    private String surname;

    private String email;

    private String phone;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "person_group", joinColumns = @JoinColumn(name = "pid", referencedColumnName = "pid"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Group> groups;


}
