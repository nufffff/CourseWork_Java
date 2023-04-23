package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alishev.springcourse.FirstSecurityApp.repositories.OrderRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.services.DopService;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final DopService dopService;

    private final PeopleRepository peopleRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, DopService dopService, PeopleRepository peopleRepository) {
        this.orderRepository = orderRepository;
        this.dopService = dopService;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/orders")
    public String getPageOrders(Model model){
        String nameUser = dopService.getNameUser();
        var person = peopleRepository.findByUsername(nameUser).get();
        model.addAttribute("orders", orderRepository.findAllByPerson(person));
        return "/order";
    }
}
