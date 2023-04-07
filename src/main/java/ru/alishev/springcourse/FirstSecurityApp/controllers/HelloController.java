package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;


import java.util.List;


@Controller
public class HelloController {

    private final ProductService productService;
    private final PersonDetailsService personDetailsService;
    @Autowired
    public HelloController(ProductService productService, PersonDetailsService personDetailsService) {
        this.productService = productService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping({"/index"})
    public String index(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        List<Product> products = this.productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("name", name);
        return "index";
    }


    @GetMapping("/admin")
    public String adminPage(Model model) {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();

        model.addAttribute("name", name);
        model.addAttribute("show",personDetailsService.showAll());
        return "admin";
    }
}
