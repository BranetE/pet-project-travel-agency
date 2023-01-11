package org.example.controller;

import org.example.dto.impl.UserDetailsImpl;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    //only for admin
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/{user_id}")
    @PreAuthorize("authentication.principal.id == #userId or hasAuthority('ADMIN')")
    public String showUserInfo(@PathVariable("user_id") long userId, Model model)
    {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/info";
    }

    @GetMapping("/{user_id}/orders")
    public String showOrdersByUser(@PathVariable("user_id") long userId, Model model)
    {
        User user = userService.getUser(userId);
        List<Order> orders = orderService.getAllOrdersByUser(user);
        model.addAttribute("orders", orders);
        return "user/orders";
    }


    @GetMapping("/{user_id}/update")
    @PreAuthorize("authentication.principal.id == #userId or hasAuthority('ADMIN')")
    public String updateUser(@PathVariable("user_id") long userId, Model model){
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/{user_id}/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "user/update";
        }
        User oldUser = userService.getUser(user.getId());
        user.setRoles(oldUser.getRoles());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.updateUser(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/{user_id}/delete")
    @PreAuthorize("authentication.principal.id == #userId or hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable("user_id") long userId)
    {
        userService.deleteUser(userId);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userDetails.getId() == userId){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/";
    }
}
