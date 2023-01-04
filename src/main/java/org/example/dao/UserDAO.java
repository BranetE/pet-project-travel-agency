package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDAO {

    void create(User user);

    void update(User user);

    User findUserById(long id);

    User findUserByEmail(String email);

    void delete(long id);

    List<User> findAll();

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

}
