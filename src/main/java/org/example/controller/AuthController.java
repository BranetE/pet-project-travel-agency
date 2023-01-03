package org.example.controller;

import org.example.dao.RoleDAO;
import org.example.model.ERole;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    RoleDAO roleDAO;
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(User user){
        if(userService.existsByEmail(user.getEmail())){
            return "User with same email already exists";
        }

        Role role = roleDAO.findByName(ERole.USER);
        if(role == null){
            role = new Role();
            role.setName(ERole.USER);
            roleDAO.create(role);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.createUser(user);
        return "index";
    }

}
