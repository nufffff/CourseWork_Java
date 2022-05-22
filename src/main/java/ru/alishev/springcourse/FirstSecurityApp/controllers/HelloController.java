package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
public class HelloController { ;

    @Autowired
    ProductService productService;

    @Autowired
    PersonDetailsService personDetailsService;

    @GetMapping({"/index"})
    public String index(Model model) {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        Iterable<Product> types = this.productService.getAll();
        List<Product> products = new ArrayList<>((Collection) types);
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
