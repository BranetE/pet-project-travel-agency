package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


// this controller is just example
@Controller
public class HomeController {

    @GetMapping
    public String home(Principal user){
        if(user == null){
            return "redirect:/login";
        }
        return "redirect:/hotel/all";
    }

}
