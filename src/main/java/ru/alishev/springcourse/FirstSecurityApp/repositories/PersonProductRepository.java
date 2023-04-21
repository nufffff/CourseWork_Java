package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.models.PersonProduct;
import ru.alishev.springcourse.FirstSecurityApp.models.PersonProductId;

import java.util.List;

@Repository
public interface PersonProductRepository extends JpaRepository<PersonProduct, PersonProductId> {
    List<PersonProduct> findAllById_Person(Person person);
}
