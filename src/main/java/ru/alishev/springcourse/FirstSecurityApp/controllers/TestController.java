package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alishev.springcourse.FirstSecurityApp.entity.PersonProduct;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;
import ru.alishev.springcourse.FirstSecurityApp.services.TestService;

import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    private final PersonDetailsService userService;

    private final ProductService productService;
    @Autowired
    public TestController(TestService testService, PersonDetailsService userService, ProductService productService) {
        this.testService = testService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/test")
    public List<Product> getAllListProduct(){
        return testService.getAllListProduct();
    }
    @GetMapping("/test1")
    public List<PersonProduct> getAllListPP(){
        var pP = testService.findAllById_Person();
        System.out.println(pP.size() + " size");
        pP.forEach(System.out::println);
        return testService.getAllPP();
    }
    @GetMapping("/sort")
    public List<Product> getAllListSortProduct(){
        return testService.getAllListSortProduct();
    }

}
