package org.example.controller;

import org.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "home";
    }
}

