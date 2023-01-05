package org.example.controller;

import org.example.model.Hotel;
import org.example.model.User;
import org.example.service.HotelService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @GetMapping
    public String index(){
        return "admin/admin";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "admin/admin_users";
    }
    @GetMapping("/hotels")
    public String getHotels(Model model){
        List<Hotel> listHotels = hotelService.getAllHotels();
        model.addAttribute("listHotels", listHotels);
        model.addAttribute("hotel", new Hotel());
        return "admin/admin_hotels";
    }
}
