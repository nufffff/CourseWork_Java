package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.entity.*;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.PersonProductId;
import ru.alishev.springcourse.FirstSecurityApp.repositories.*;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TestService {

    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final PeopleRepository peopleRepository;

    private final OrderRepository orderRepository;
    private final PersonProductRepository pPRepository;
    @Autowired
    public TestService(OrderProductRepository orderProductRepository, ProductRepository productRepository, PeopleRepository peopleRepository, OrderRepository orderRepository, PersonProductRepository pPRepository) {
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.peopleRepository = peopleRepository;
        this.orderRepository = orderRepository;
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
    @Transactional
    public void saveOrderAllFuncition(OrderProduct orderProduct, List<PersonProduct> pP) {
        removeListPersonAndProduct(pP);
        System.out.println("mda1");
        orderProductRepository.save(orderProduct);
    }
    @Transactional
    public void saveOrder(Order order) {
        order.setDate(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
    }
    @Transactional
    public void removeListPersonAndProduct(List<PersonProduct> listPerProd){
        for(var x: listPerProd){
            var person = x.getId().getPerson();
            var product = x.getId().getProduct();
            person.getProductList().removeIf( y -> y.getId() == x.getId().getProduct().getId());
            product.getPersonList().removeIf(y -> y.getId() == person.getId());
            peopleRepository.save(person);
            productRepository.save(product);
        }
    }
    @Transactional
    public void findAllById(PersonProductId id) {
        var pPOptional = pPRepository.findById(id);
        PersonProduct pP;
        if(pPOptional.isPresent()){
            pP = pPOptional.get();
            pP.incAmount();

        }else{
            pP = new PersonProduct(id, 1);

        }
        pPRepository.save(pP);

    }
}
