package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alishev.springcourse.FirstSecurityApp.entity.OrderProduct;
import ru.alishev.springcourse.FirstSecurityApp.repositories.OrderRepository;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.services.DopService;
import ru.alishev.springcourse.FirstSecurityApp.services.OrderProductService;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final DopService dopService;

    private final PeopleRepository peopleRepository;
    private final OrderProductService orderProductService;

    @Autowired
    public OrderController(OrderRepository orderRepository, DopService dopService, PeopleRepository peopleRepository, OrderProductService orderProductService) {
        this.orderRepository = orderRepository;
        this.dopService = dopService;
        this.peopleRepository = peopleRepository;
        this.orderProductService = orderProductService;
    }

    @GetMapping("/orders")
    public String getPageOrders(Model model){
        String nameUser = dopService.getNameUser();
        var person = peopleRepository.findByUsername(nameUser).get();
        model.addAttribute("orders", orderRepository.findAllByPerson(person));
        model.addAttribute("name", nameUser);
        return "/order";
    }

    @GetMapping("/order/check/{id}")
    public String showOrderById(Model model, @PathVariable("id") int id){

        var productsOfOrder = orderProductService.getOrderProduct(id);
        model.addAttribute("productsOfOrder", productsOfOrder);
        model.addAttribute("orderId", id);
        model.addAttribute("name", dopService.getNameUser());


        return "/orderById";
    }


}
