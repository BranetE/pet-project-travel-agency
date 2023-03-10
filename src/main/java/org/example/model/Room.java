package org.example.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "number", nullable = false)
    @NotNull(message = "number is required")
    @Min(value = 0, message = "must be more than 0")
    private long number;

    @Column(name = "capacity")

    @NotNull(message = "capacity is required")
    @Min(value = 1, message = "must be more than 0")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE})
    @Fetch(value = FetchMode.SELECT)
    private List<Order> orders = new ArrayList<>();

    public Room() {
    }

    public Room(long id, long number, int capacity, Hotel hotel, List<Order> orders) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.hotel = hotel;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && number == room.number && capacity == room.capacity && Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, capacity, hotel);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", capacity=" + capacity +
                ", hotel=" + hotel +
                ", orders=" + orders +
                '}';
    }
}
