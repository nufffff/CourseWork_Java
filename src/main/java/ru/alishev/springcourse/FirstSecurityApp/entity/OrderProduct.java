package ru.alishev.springcourse.FirstSecurityApp.entity;

import lombok.*;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.OrderProductId;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProduct {
    @EmbeddedId
    private OrderProductId id;



    private int amount;


}
