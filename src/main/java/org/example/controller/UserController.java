package org.example.controller;

import org.example.dto.impl.UserDetailsImpl;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("hasAuthority('ADMIN') or @userServiceImpl.getUser(#userId).id == #userDetails.id")
    public String showUserInfo(@PathVariable("user_id") long userId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/info";
    }

    @GetMapping("/{user_id}/orders")
    @PreAuthorize("hasAuthority('ADMIN') or @userServiceImpl.getUser(#userId).id == #userDetails.id")
    public String showOrdersByUser(@PathVariable("user_id") long userId, Model model)
    {
        User user = userService.getUser(userId);
        List<Order> orders = orderService.getAllOrdersByUser(user);
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    @GetMapping("/create")
    public String createUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "user/register";
        }
        userService.createUser(user);
        return "index";
    }

    @GetMapping("/{user_id}/update")
    @PreAuthorize("hasAuthority('ADMIN') or @userServiceImpl.getUser(#userId).id == #userDetails.id")
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
        userService.updateUser(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/{user_id}/delete")
    @PreAuthorize("hasAuthority('ADMIN') or @userServiceImpl.getUser(#userId).id == #userDetails.id")
    public String deleteUser(@PathVariable("user_id") long userId)
    {
        userService.deleteUser(userId);
        return "";
    }
}
