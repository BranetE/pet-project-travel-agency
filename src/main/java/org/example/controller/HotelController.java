package org.example.controller;

import org.example.model.Hotel;
import org.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/all")
    public String getAll(Model model){
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotel/hotels";
    }

    @GetMapping("/add")
    public String createHotel(Model model){
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        return "hotel/create";
    }

    @PostMapping("/add")
    public String createHotel(@Valid @ModelAttribute("hotel") Hotel hotel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "hotel/create";
        }
        hotelService.createHotel(hotel);
        return "redirect:/hotel/all";
    }

    @GetMapping("/{hotel_id}/update")
    public String updateHotel(@PathVariable("hotel_id") long hotelId, Model model)
    {
        Hotel hotel = hotelService.getHotel(hotelId);
        model.addAttribute("hotel", hotel);
        return "hotel/update";
    }

    @PostMapping("/{hotel_id}/update")
    public String updateHotel(@Valid @ModelAttribute("hotel") Hotel hotel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "hotel/update";
        }
        hotelService.updateHotel(hotel);
        return "redirect:/hotel/all";
    }

    @GetMapping("/{hotel_id}/delete")
    public String deleteHotel(@PathVariable("hotel_id") long hotelId){
        hotelService.deleteHotel(hotelId);
        return "redirect:/hotel/all";
    }

}
