package ru.alishev.springcourse.FirstSecurityApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getById(int id) {
        return this.productRepository.findById(id);
    }

    public List<Product> getAll() {
        return  productRepository.findAll();
    }
    @Transactional
    public void addProduct(Product product) {
        this.productRepository.save(product);
    }
    @Transactional
    public void delete(Product product, Person user) {
        var personList = product.getPersonList();
        personList.removeIf(x-> x.getId() == user.getId());
        product.setPersonList(personList);
        productRepository.save(product);
    }
}
