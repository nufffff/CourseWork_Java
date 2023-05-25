package ru.alishev.springcourse.FirstSecurityApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alishev.springcourse.FirstSecurityApp.entity.*;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.OrderProductId;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.PersonProductId;
import ru.alishev.springcourse.FirstSecurityApp.services.DopService;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;
import ru.alishev.springcourse.FirstSecurityApp.services.TestService;

@Controller
public class CartController {

    private final PersonDetailsService userService;

    private final ProductService productService;
    private final TestService testService;
    private final DopService dopService;

    @Autowired
    public CartController(PersonDetailsService userService, ProductService productService, TestService testService, DopService dopService) {
        this.userService = userService;
        this.productService = productService;
        this.testService = testService;
        this.dopService = dopService;
    }

    @GetMapping("profile/cart-product")
    public String cartProduct(Model model) {


        String name = dopService.getNameUser();

        Person user = userService.getPerson(name);
        var pPById_Person = testService.findAllById_Person();
        int total = this.findSum(user);
        model.addAttribute("listPP", pPById_Person);
        model.addAttribute("total", total);
        model.addAttribute("name", name);
        return "profile/cart-product";
    }

    private int findSum(Person user) {
        var productList = user.getProductList();
        int sum = 0;
        var perProd = testService.findAllById_Person();
        for (PersonProduct pP : perProd) {
            sum += pP.getId().getProduct().getPrice() * pP.getAmount();
        }


        return sum;
    }

    @GetMapping("addToCart/{id}")
    public String addToCart(@PathVariable("id") int productId) {

        String nameUser = dopService.getNameUser();

        var user = userService.getPerson(nameUser);
        var product = productService.getById(productId).get();

        var id = new PersonProductId(user, product);
        testService.findAllById(id);




        return "redirect:/profile/cart-product";
    }


    @GetMapping({"profile/cart-product/delete/{id}"})
    public String delete(@PathVariable("id") int productId) {

        String nameUser = dopService.getNameUser();

        Person user = userService.getPerson(nameUser);
        Product product = productService.getById(productId).get();

        var pPId = new PersonProductId(user, product);
        var pP = testService.getPP(pPId);

        testService.deleteNewFeature(pP);
        //userService.delete(user,product);
        //productService.delete(product,user);


        return "redirect:/profile/cart-product";
    }

    @GetMapping({"profile/cart-product/incAmount/{id}"})
    public String addAmount(@PathVariable("id") int productId) {

        String nameUser = dopService.getNameUser();

        Person user = userService.getPerson(nameUser);
        Product product = this.productService.getById(productId).get();
        testService.addAmount(user, product);


        return "redirect:/profile/cart-product";
    }

    @GetMapping({"profile/cart-product/reduceAmount/{id}"})
    public String reduceAmount(@PathVariable("id") int productId) {

        String nameUser = dopService.getNameUser();

        Person user = userService.getPerson(nameUser);
        Product product = this.productService.getById(productId).get();
        var pPId = new PersonProductId(user, product);
        var pP = testService.getPP(pPId);
        testService.reduce(pP);


        return "redirect:/profile/cart-product";
    }
    @GetMapping("/profile/cart-product/createOrder")
    public String createOrder() {
        System.out.println("created order");
        String nameUser = dopService.getNameUser();

        var user = userService.getPerson(nameUser);

        var pP = testService.findAllById_Person();
        var order = new Order();
        order.setTotal(findSum(user));
        order.setPerson(user);
        testService.saveOrder(order);
        for(var x: pP){


            var orderProduct = new OrderProduct();
            var idOP = new OrderProductId();
            idOP.setOrder(order);
            idOP.setProduct(x.getId().getProduct());
            orderProduct.setId(idOP);
            orderProduct.setAmount(x.getAmount());
            testService.saveOrderAllFuncition(orderProduct, pP);

        }

        return "redirect:/orders";
    }


}
