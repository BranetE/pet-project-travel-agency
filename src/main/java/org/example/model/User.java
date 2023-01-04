package org.example.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles = new HashSet<>();

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
    public String phone;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "password is required")
    public String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {
    }

    public User(long id, Set<Role> roles, String lastName, String firstName, String email,
                String phone, String password, List<Order> orders) {
        this.id = id;
        this.roles = roles;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
