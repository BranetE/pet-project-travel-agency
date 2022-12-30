package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDAO {

    void create(User user);

    void update(User user);

    User read(long id);

    void delete(long id);

    List<User> findAll();
}
