package com.example.spring.demo.group;

import com.example.spring.demo.group.exception.GroupExistsException;
import com.example.spring.demo.group.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {

    List<Group> getAll();

    Group findById(Long id) throws GroupNotFoundException;

    Group save(Group group) throws GroupExistsException;

    void update(Group group);

    void delete(long id) throws GroupNotFoundException;

    void deleteAll();

    void saveAndFlush(Group group);
}