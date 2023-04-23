package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.entity.Person;
import ru.alishev.springcourse.FirstSecurityApp.entity.PersonProduct;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.PersonProductId;

import java.util.List;

@Repository
public interface PersonProductRepository extends JpaRepository<PersonProduct, PersonProductId> {
    List<PersonProduct> findAllById_Person(Person person);
}
