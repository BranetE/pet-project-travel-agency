package org.example.controller;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// this controller is just example
@Controller
public class HomeController {
    @Autowired
    UserDAO userDAO;
    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> listUsers = userDAO.findAll();
        model.addAttribute("listUsers", listUsers);
        return "user/users";
    }
}
