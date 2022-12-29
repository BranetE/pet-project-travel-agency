package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public void create(User user) {
        userDAO.createUser(user);
    }

    @Override
    public void update(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User read(long id) {
        return userDAO.getUer(id);
    }

    @Override
    public void delete(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.getAllUsers();
    }
}
