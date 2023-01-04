package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    void updateUser(User User);

    User getUser(long id);

    void deleteUser(long id);

    List<User> getAllUsers();

    Boolean existsByEmail(String email);
}
