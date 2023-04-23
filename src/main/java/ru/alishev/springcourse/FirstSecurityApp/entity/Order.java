package ru.alishev.springcourse.FirstSecurityApp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Timestamp date;
    private int total;


    @ManyToMany(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "Order_Product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> prodList;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;







}
