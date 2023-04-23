package ru.alishev.springcourse.FirstSecurityApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Users")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @Email
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @ManyToMany(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "User_Product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;
    @JsonIgnore

    @OneToMany(mappedBy = "person")
    private List<Order> order;

    // Конструктор по умолчанию нужен для Spring

    public Person() {
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Person(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.name();
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return  " username='" + username + '\'' + ", email=" + email + ", role='" + role.name() + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && username.equals(person.username) && password.equals(person.password) && email.equals(person.email) && role == person.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role);
    }
}