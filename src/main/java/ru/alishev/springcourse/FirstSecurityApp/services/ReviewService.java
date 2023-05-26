package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.entity.Review;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ReviewRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final PeopleRepository peopleRepository;
    private final DopService dopService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, PeopleRepository peopleRepository, DopService dopService) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.peopleRepository = peopleRepository;
        this.dopService = dopService;
    }

    @Transactional
    public void saveReview(int product_id, Review review){
        String name = dopService.getNameUser();
        var person = peopleRepository.findByUsername(name).get();
        var product = productRepository.findById(product_id).get();
        review.setPerson(person);
        review.setProduct(product);
        review.setDate(new Timestamp(System.currentTimeMillis()));
        reviewRepository.save(review);

        //add review to person
        if(person.getReviews() == null){
            person.setReviews(new ArrayList<>(List.of(review)));
        }else{
            person.getReviews().add(review);
        }

        //add review to product
        if(product.getReviews() == null){
            product.setReviews(new ArrayList<>(List.of(review)));
        }else{
            product.getReviews().add(review);
        }

    }

    public List<Review> getRevByProductId(int id) {

        return reviewRepository.findAllByProduct(productRepository.findById(id).get());
    }
}
