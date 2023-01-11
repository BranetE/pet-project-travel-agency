package org.example.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "start_time", nullable = false)
//    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "start time is required")
    private LocalDate startTime;

    @Column(name = "end_time", nullable = false)
//    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "end time is required")
    private LocalDate endTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Order() {
    }

    public Order(long id, LocalDate startTime, LocalDate endTime, User user, Room room) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(startTime, order.startTime) && Objects.equals(endTime, order.endTime) && Objects.equals(user, order.user) && Objects.equals(room, order.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, user, room);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", user=" + user +
                ", room=" + room +
                '}';
    }
}
