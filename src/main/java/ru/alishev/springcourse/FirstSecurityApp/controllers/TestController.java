package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/test")
    public List<Product> getAllListProduct(){
        productRepository.findAll().forEach(System.out::println);
        return productRepository.findAll();
    }
}
