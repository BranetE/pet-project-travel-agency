package org.example.controller;

import org.example.model.Hotel;
import org.example.model.Room;
import org.example.service.HotelService;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    HotelService hotelService;

    @GetMapping("/all/{hotel_id}")
    public String allByHotel(@PathVariable("hotel_id") long hotelId, Model model){
        Hotel hotel = hotelService.getHotel(hotelId);
        List<Room> rooms = roomService.getAllRoomsByHotel(hotel);
        model.addAttribute("rooms", rooms);
        model.addAttribute("hotel_id", hotelId);
        return "room/rooms";
    }

    @GetMapping("/add/{hotel_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createRoom(@PathVariable("hotel_id") long hotelId, Model model)
    {
        Room room = new Room();
        model.addAttribute("room", room);
        return "room/create";

    }

    @PostMapping("/add/{hotel_id}")
    public String createRoom(@PathVariable("hotel_id") long hotelId, @Valid @ModelAttribute("room") Room room, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "room/create";
        }
        Hotel hotel = hotelService.getHotel(hotelId);
        room.setHotel(hotel);
        roomService.createRoom(room);
        return "redirect:/room/all/" + hotelId;
    }
    @GetMapping("/{room_id}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateRoom(@PathVariable("room_id") long roomId, Model model)
    {
        Room room = roomService.getRoom(roomId);
        model.addAttribute("room", room);
        return "room/update";

    }

    @PostMapping("/{room_id}/update")
    public String updateRoom(@PathVariable("room_id") long roomId, @Valid @ModelAttribute("room") Room room, BindingResult bindingResult)
    {
        Room oldRoom = roomService.getRoom(roomId);
        room.setHotel(oldRoom.getHotel());
        room.setOrders(oldRoom.getOrders());
        long hotelId = room.getHotel().getId();
        if(bindingResult.hasErrors()){
            return "room/update";
        }
        roomService.updateRoom(room);
        return "redirect:/room/all/" + hotelId;
    }

    @GetMapping("/{room_id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteRoom(@PathVariable("room_id") long roomId){
        Room room = roomService.getRoom(roomId);
        long hotelId = room.getHotel().getId();
        roomService.deleteRoom(roomId);
        return "redirect:/room/all/" + hotelId;
    }
}
