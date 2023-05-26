package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;
import ru.alishev.springcourse.FirstSecurityApp.entity.Review;
import ru.alishev.springcourse.FirstSecurityApp.services.DopService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;
import ru.alishev.springcourse.FirstSecurityApp.services.ReviewService;

@Controller
public class ProductController {

    private final ProductService productService;

    private final ReviewService reviewService;


    private final DopService dopService;

    @Autowired
    public ProductController(ProductService productService, ReviewService reviewService, DopService dopService) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.dopService = dopService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") int id, Model model, @ModelAttribute("rev") Review review) {
        String name = dopService.getNameUser();

        var productOpt = this.productService.getById(id);
        if (productOpt.isPresent()) {
            Product product = (Product)productOpt.get();
            model.addAttribute("product", product);
            model.addAttribute("name", name);
            model.addAttribute("reviews", reviewService.getRevByProductId(id));
            return "product";
        } else {
            return "redirect:/";
        }
    }
}
