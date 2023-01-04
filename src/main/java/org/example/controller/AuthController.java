package org.example.controller;

import com.mchange.util.AlreadyExistsException;
import org.example.dao.RoleDAO;
import org.example.dao.UserDAO;
import org.example.model.ERole;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    RoleDAO roleDAO;
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(User user) throws AlreadyExistsException {
        if(userDAO.existsByEmail(user.getEmail())){
            throw new AlreadyExistsException("User already exists with this email ");
        }
        if(userDAO.existsByPhone(user.getEmail())){
            throw new AlreadyExistsException("User already exists with this email ");
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
        userDAO.create(user);

        return "redirect:/";
    }

}
