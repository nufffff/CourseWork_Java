package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.List;
import java.util.Optional;


@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }

    public Person getPerson(String username) {
        return peopleRepository.findByUsername(username).get();
    }

    public void update(Person user, Product product) {

        List<Product> productlist = peopleRepository.findByUsername(user.getUsername()).get().getProductList();
        productlist.add(product);
        user.setProductList(productlist);
        peopleRepository.save(user);
    }

    public void delete(Person user, Product product) {

        List<Product> productlist = peopleRepository.findByUsername(user.getUsername()).get().getProductList();
        productlist.removeIf(x-> x.getId() == product.getId());
        user.setProductList(productlist);
        peopleRepository.save(user);
    }

    public List<Person> showAll(){
        return peopleRepository.findAll();
    }
}
