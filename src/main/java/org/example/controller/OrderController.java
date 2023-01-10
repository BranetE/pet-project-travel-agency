package org.example.controller;

import org.example.dto.impl.UserDetailsImpl;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.OrderService;
import org.example.service.RoomService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @GetMapping("/{order_id}")
    public String showOrder(@PathVariable("order_id") long orderId, Model model){
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "order/info";
    }

    // for admin
    @GetMapping("/all")
    public String showAllOrders(Model model)
    {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    @GetMapping("/book/{room_id}")
    public String createOrder(@PathVariable("room_id") long roomId, Model model){
        Order order = new Order();
        List<String[]> busyDates = orderService.getAllBusyDates(roomId);
        model.addAttribute("order", order);
        model.addAttribute("busyDates", busyDates);
        return "order/create";
    }

    @PostMapping("/book/{room_id}")
    public String createOrder(@PathVariable("room_id") long roomId,
                              @Valid @ModelAttribute("order") Order order, BindingResult bindingResult,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(bindingResult.hasErrors()){
            return "order/create";
        }
        order.setRoom(roomService.getRoom(roomId));
        order.setUser(userService.getUser(userDetails.getId()));
        orderService.createOrder(order);
        return "redirect:/order/"+order.getId();
    }

    @GetMapping("/{order_id}/update")
    public String updateOrder(@PathVariable("order_id") long orderId, Model model)
    {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "order/update";
    }

    @PostMapping("/{order_id}/update")
    public String updateOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "order/update";
        }
        orderService.updateOrder(order);
        return "redirect:/order/"+order.getId();
    }

    @GetMapping("/{order_id}/delete")
    public String deleteOrder(@PathVariable("order_id") long orderId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.deleteOrder(orderId);
        return "redirect:user/" + user.getId() + "/orders";
    }

}
