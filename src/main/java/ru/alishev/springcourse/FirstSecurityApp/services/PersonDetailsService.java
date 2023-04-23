package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.entity.Person;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
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
    @Transactional
    public void savePerson(Person user) {
        peopleRepository.save(user);
    }
    @Transactional
    public void delete(Person user, Product product) {

        var productList = user.getProductList();
        productList.removeIf(x-> x.getId() == product.getId());
        user.setProductList(productList);
        peopleRepository.save(user);
    }

    public List<Person> showAll(){
        return peopleRepository.findAll();
    }
}
