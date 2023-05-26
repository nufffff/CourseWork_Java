package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.entity.Review;
import ru.alishev.springcourse.FirstSecurityApp.services.ReviewService;


@Controller
public class ReviewsController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/addReviews/{id}")
    public String addReviews(@ModelAttribute("rev") Review review, @PathVariable("id") int id){
        reviewService.saveReview(id, review);

        return "redirect:/" + id;
    }


}
