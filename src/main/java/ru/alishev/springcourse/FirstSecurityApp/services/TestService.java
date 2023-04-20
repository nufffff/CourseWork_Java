package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllListProduct(){
        System.out.println("fuck");
        return productRepository.findAll();
    }

    public List<Product> getAllListSortProduct(){
        var listProduct = productRepository.findAll();
        listProduct.sort(Comparator.comparingInt(Product::getPrice));
        return listProduct;

    }
}
