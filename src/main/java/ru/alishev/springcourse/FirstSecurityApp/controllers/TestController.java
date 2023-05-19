package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alishev.springcourse.FirstSecurityApp.entity.PersonProduct;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;
import ru.alishev.springcourse.FirstSecurityApp.services.DopService;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;
import ru.alishev.springcourse.FirstSecurityApp.services.TestService;

import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    private final PersonDetailsService userService;

    private final DopService dopService;

    private final ProductService productService;

    private final ProductRepository productRepository;
    @Autowired
    public TestController(TestService testService, PersonDetailsService userService, DopService dopService, ProductService productService, ProductRepository productRepository) {
        this.testService = testService;
        this.userService = userService;
        this.dopService = dopService;
        this.productService = productService;
        this.productRepository = productRepository;
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

    @GetMapping("/test5")
    public List<Product> test5(){
        String name = dopService.getNameUser();
        var dop = userService.getPerson(name);
        System.out.println(dop.getProductList().size());
        return userService.getPerson(name).getProductList();
    }
    @GetMapping("/test6/{name}")
    @ResponseBody
    public List<Product> test6(@PathVariable("name")String name){
        System.out.println("hello");
        return productRepository.findByNameStartingWithIgnoreCase(name);
    }
}
