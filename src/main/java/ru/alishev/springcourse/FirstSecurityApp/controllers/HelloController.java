package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;
import ru.alishev.springcourse.FirstSecurityApp.services.DopService;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;


import java.util.List;


@Controller
public class HelloController {

    private final ProductService productService;
    private final PersonDetailsService personDetailsService;

    private final DopService dopService;
    @Autowired
    public HelloController(ProductService productService, PersonDetailsService personDetailsService, DopService dopService) {
        this.productService = productService;
        this.personDetailsService = personDetailsService;
        this.dopService = dopService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        String name = dopService.getNameUser();
        model.addAttribute("products", productService.getAll());
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
    @GetMapping("/lol")
    public String getTest() {


        return "/test/feat_sort";
    }
}
