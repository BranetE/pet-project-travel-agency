package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.exception.NullEntityReferenceException;
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
    public void createUser(User user) {
        if(user == null){
            throw new NullEntityReferenceException("Entity user is null in create method");
        }
        userDAO.create(user);
    }

    @Override
    public void updateUser(User user) {
        if(user == null){
            throw new NullEntityReferenceException("Entity user is null in update method");
        }
        userDAO.update(user);
    }

    @Override
    public User getUser(long id) {
        return userDAO.findUserById(id);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userDAO.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhone(String phone) { return  userDAO.existsByPhone(phone); }
}
