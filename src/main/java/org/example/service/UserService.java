package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {

    void create(User user);

    void update(User User);

    User read(long id);

    void delete(long id);

    List<User> findAll();
}
