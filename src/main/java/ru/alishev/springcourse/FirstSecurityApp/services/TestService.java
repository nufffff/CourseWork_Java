package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.models.PersonProduct;
import ru.alishev.springcourse.FirstSecurityApp.models.PersonProductId;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PersonProductRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ProductRepository;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TestService {

    private final ProductRepository productRepository;
    private final PeopleRepository peopleRepository;

    private final PersonProductRepository pPRepository;
    @Autowired
    public TestService(ProductRepository productRepository, PeopleRepository peopleRepository, PersonProductRepository pPRepository) {
        this.productRepository = productRepository;
        this.peopleRepository = peopleRepository;
        this.pPRepository = pPRepository;
    }

    public List<Product> getAllListProduct(){
        System.out.println("fuck");
        return productRepository.findAll();
    }

    public List<Product> getAllListSortProduct(){
        var listProduct = productRepository.findAll();
        listProduct.sort(Comparator.comparingInt(Product::getPrice));
        return listProduct;

    }
    public List<PersonProduct> getAllPP(){
        return pPRepository.findAll();
    }
    @Transactional
    public void addAmount(Person user, Product product) {
        var pPId = new PersonProductId(user, product);
        var pP = pPRepository.findById(pPId).get();
        pP.incAmount();
        pPRepository.save(pP);
    }

    public List<PersonProduct> findAllById_Person() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        var person = peopleRepository.findByUsername(authentication.getName()).get();

        var pP = pPRepository.findAllById_Person(person);
        pP.sort(Comparator.comparing(x -> x.getId().getProduct().getName()));
        return pP;
    }
    public PersonProduct getPP(PersonProductId pPID){
        return pPRepository.findById(pPID).get();
    }
    @Transactional
    public void reduce(PersonProduct pP) {
        pP.reduceAmount();
        pPRepository.save(pP);
    }

    @Transactional
    public void deleteNewFeature(PersonProduct pP) {
        pPRepository.delete(pP);
    }
}
