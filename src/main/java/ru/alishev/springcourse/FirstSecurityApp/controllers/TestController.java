package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;
import ru.alishev.springcourse.FirstSecurityApp.services.TestService;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public List<Product> getAllListProduct(){
        return testService.getAllListProduct();
    }
    @GetMapping("/sort")
    public List<Product> getAllListSortProduct(){
        return testService.getAllListSortProduct();
    }
}
