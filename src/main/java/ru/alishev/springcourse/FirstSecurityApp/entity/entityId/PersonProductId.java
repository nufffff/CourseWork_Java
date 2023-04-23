package ru.alishev.springcourse.FirstSecurityApp.entity.entityId;

import lombok.*;
import ru.alishev.springcourse.FirstSecurityApp.entity.Person;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonProductId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonProductId that = (PersonProductId) o;
        return person.equals(that.person) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, product);
    }
}
