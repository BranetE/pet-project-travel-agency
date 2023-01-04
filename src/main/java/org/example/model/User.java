package org.example.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Role role;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "last name is required")
    public String lastName;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "first name is required")
    public String firstName;

    @Column(name = "email")
    public String email;

    @Column(name = "phone", nullable = false, unique = true)
    @NotBlank(message = "telephone is required")
    public long phone;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "password is required")
    public String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {
    }

    public User(long id, Role role, String lastName, String firstName, String email, long phone, String password, List<Order> orders) {
        this.id = id;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        User user = (User) o;
        return id == user.id && phone == user.phone && role == user.role && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, lastName, firstName, email, phone, password, orders);
    }
}
