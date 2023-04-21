package ru.alishev.springcourse.FirstSecurityApp.controllers;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.models.PersonProductId;
import ru.alishev.springcourse.FirstSecurityApp.models.Product;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;
import ru.alishev.springcourse.FirstSecurityApp.services.ProductService;
import ru.alishev.springcourse.FirstSecurityApp.services.TestService;

@Controller
public class CartController {

    private final PersonDetailsService userService;

    private final ProductService productService;
    private final TestService testService;
    @Autowired
    public CartController(PersonDetailsService userService, ProductService productService, TestService testService) {
        this.userService = userService;
        this.productService = productService;
        this.testService = testService;
    }

    @GetMapping({"profile/cart-product"})
    public String cartProduct(Model model) {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();

        Person user =  userService.getPerson(authentication.getName());
        var pPById_Person = testService.findAllById_Person();
        int total = this.findSum(user);
        model.addAttribute("listPP", pPById_Person);
        model.addAttribute("total", total);
        model.addAttribute("name", name);
        return "profile/cart-product";
    }

    private int findSum(Person user) {
        List<Product> productList = user.getProductList();
        int sum = 0;
        Product p;
        for(Iterator var4 = productList.iterator(); var4.hasNext(); sum += p.getPrice()) {
            p = (Product)var4.next();
        }
        return sum;
    }

    @GetMapping({"addToCart/{id}"})
    public String addToCart(@PathVariable("id") int productId) {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        Person user = userService.getPerson(authentication.getName());
        Product product = this.productService.getById(productId).get();

        List<Person> userList = new ArrayList();
        userList.add(user);
        product.setPersonList(userList);

        userService.update(user,product);
        productService.addProduct(product);

        int total = this.findSum(user);

        return "redirect:/profile/cart-product";
    }


    @GetMapping({"profile/cart-product/delete/{id}"})
    public String delete(@PathVariable("id") int productId){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        Person user = userService.getPerson(authentication.getName());
        Product product = productService.getById(productId).get();

        var pPId = new PersonProductId(user, product);
        var pP = testService.getPP(pPId);

        testService.deleteNewFeature(pP);
        //userService.delete(user,product);
        //productService.delete(product,user);




        return "redirect:/profile/cart-product";
    }
    @GetMapping({"profile/cart-product/incAmount/{id}"})
    public String addAmount(@PathVariable("id") int productId){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        Person user = userService.getPerson(authentication.getName());
        Product product = this.productService.getById(productId).get();
        testService.addAmount(user, product);



        return "redirect:/profile/cart-product";
    }
    @GetMapping({"profile/cart-product/reduceAmount/{id}"})
    public String reduceAmount(@PathVariable("id") int productId){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        Person user = userService.getPerson(authentication.getName());
        Product product = this.productService.getById(productId).get();
        var pPId = new PersonProductId(user, product);
        var pP = testService.getPP(pPId);
        testService.reduce(pP);



        return "redirect:/profile/cart-product";
    }


}
