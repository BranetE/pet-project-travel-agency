package org.example.controller;

import org.example.model.Order;
import org.example.service.OrderService;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RoomService roomService;

    @GetMapping("/book/{room_id")
    public String createOrder(@PathVariable("room_id") long roomId, Model model){
        Order order = new Order();
        order.setRoom(roomService.getRoom(roomId));
        model.addAttribute("order", order);
        return "create-order";
    }


}
