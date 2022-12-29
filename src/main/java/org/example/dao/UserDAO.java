package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDAO {

    void createUser(User user);

    void updateUser(User user);

    User getUer(long id);

    void deleteUser(long id);

    List<User> getAllUsers();
}
