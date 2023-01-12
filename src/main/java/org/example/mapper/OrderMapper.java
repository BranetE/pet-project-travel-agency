package org.example.mapper;

import org.example.dto.OrderDTO;
import org.example.model.Order;
import org.example.model.Room;
import org.example.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderMapper {
    public static OrderDTO convertToDto(Order order){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String startTime = order.getStartTime().format(formatter);
        String endTime = order.getEndTime().format(formatter);
        return new OrderDTO(order.getId(), startTime, endTime, order.getUser().getId(), order.getRoom().getId());
    }

    public static Order convertToEntity(OrderDTO orderDTO, User user, Room room){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        LocalDate startTime = LocalDate.parse(orderDTO.getStartTime(), formatter);
        LocalDate endTime = LocalDate.parse(orderDTO.getEndTime(), formatter);
        return new Order(orderDTO.getId(), startTime, endTime, user, room);
    }
}
