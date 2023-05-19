package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    String QUERY = "select * from Product where name like '";
    default String getQuery(String name){

        return name;
    }
    Optional<Product> findByName(String name);

    Optional<List<Product>> findAllByNameContainsIgnoreCase(String name);
    @Query("SELECT p FROM Product p  WHERE LOWER(p.name) LIKE LOWER(concat(:name, '%'))")
    List<Product> findByNameStartingWithIgnoreCase(@Param("name") String name);
}

