package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    public ProductController() {
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") int id, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();

        Optional<Product> productOpt = this.productService.getById(id);
        if (productOpt.isPresent()) {
            Product product = (Product)productOpt.get();
            model.addAttribute("product", product);
            model.addAttribute("name", name);
            return "product";
        } else {
            return "redirect:/";
        }
    }
}
