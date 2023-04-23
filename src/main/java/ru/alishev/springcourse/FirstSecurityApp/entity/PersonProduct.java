package ru.alishev.springcourse.FirstSecurityApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.PersonProductId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonProduct {
    @EmbeddedId
    private PersonProductId id;



    private int amount;

    public void incAmount(){
        this.amount++;
    }
    public void reduceAmount(){
        this.amount--;
    }


}
