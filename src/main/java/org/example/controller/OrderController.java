package org.example.controller;

import org.example.dto.OrderDTO;
import org.example.dto.impl.UserDetailsImpl;
import org.example.mapper.OrderMapper;
import org.example.model.Order;
import org.example.service.OrderService;
import org.example.service.RoomService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
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
    @PreAuthorize("hasAuthority('ADMIN') or @orderServiceImpl.getOrder(#orderId).user.id == #userDetails.id")
    public String showOrder(@PathVariable("order_id") long orderId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        OrderDTO order = OrderMapper.convertToDto(orderService.getOrder(orderId));
        model.addAttribute("order", order);
        return "order/info";
    }

    // for admin
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAllOrders(Model model)
    {
        List<OrderDTO> orders = orderService.getAllOrders().stream().map(OrderMapper::convertToDto).toList();
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    @GetMapping("/book/{room_id}")
    public String createOrder(@PathVariable("room_id") long roomId, Model model){
        Order order = new Order();
//        List<String[]> busyDates = orderService.getAllByRoom(roomId);
        List<OrderDTO> orders = orderService.getAllByRoom(roomId).stream().map(OrderMapper::convertToDto).toList();
        model.addAttribute("order", order);
        model.addAttribute("busyDates", orders);
        return "order/create";
    }

    @PostMapping("/book/{room_id}")
    public String createOrder(@PathVariable("room_id") long roomId,
                              @Valid @ModelAttribute("order") Order order, BindingResult bindingResult,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(bindingResult.hasErrors()){
            return "redirect:/order/book/" + roomId;
        }
        order.setRoom(roomService.getRoom(roomId));
        order.setUser(userService.getUser(userDetails.getId()));
        orderService.createOrder(order);
        return "redirect:/order/"+order.getId();
    }

    @GetMapping("/{order_id}/update")
    @PreAuthorize("hasAuthority('ADMIN') or @orderServiceImpl.getOrder(#orderId).user.id == #userDetails.id")
    public String updateOrder(@PathVariable("order_id") long orderId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        Order order = orderService.getOrder(orderId);
        List<OrderDTO> orders = orderService.getAllByRoom(order.getRoom().getId()).stream().map(OrderMapper::convertToDto).toList();
        model.addAttribute("busyDates", orders);
        model.addAttribute("order", order);
        return "order/update";
    }

    @PostMapping("/{order_id}/update")
    public String updateOrder(@PathVariable("order_id") long orderId, @Valid @ModelAttribute("order") Order order, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "redirect:/order/" + orderId +"/update";
        }
        Order oldOrder = orderService.getOrder(orderId);
        order.setUser(oldOrder.getUser());
        order.setRoom(oldOrder.getRoom());
        orderService.updateOrder(order);
        return "redirect:/order/"+order.getId();
    }

    @GetMapping("/{order_id}/delete")
    @PreAuthorize("hasAuthority('ADMIN') or @orderServiceImpl.getOrder(#orderId).user.id == #userDetails.id")
    public String deleteOrder(@PathVariable("order_id") long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails,
                              SecurityContextHolderAwareRequestWrapper request)
    {
        orderService.deleteOrder(orderId);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        if(authorities.contains(new SimpleGrantedAuthority("ADMIN"))){
            return "redirect:/order/all";
        }else {
            return "redirect:/user/" + userDetails.getId() + "/orders";
        }
    }

}
