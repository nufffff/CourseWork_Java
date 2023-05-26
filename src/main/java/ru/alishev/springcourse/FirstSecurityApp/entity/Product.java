package ru.alishev.springcourse.FirstSecurityApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    private String description;



    private Integer price;


    private String image;
    @JsonIgnore
    @ManyToMany(
            mappedBy = "productList",
            fetch = FetchType.EAGER
    )
    private List<Person> personList;
    @JsonIgnore
    @ManyToMany(
            mappedBy = "prodList",
            fetch = FetchType.EAGER
    )
    private List<Order> ordersList;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;




    public Product() {

    }

    public List<Review> getReviews() {
        return reviews;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && name.equals(product.name) && price.equals(product.price) && image.equals(product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image);
    }
}
