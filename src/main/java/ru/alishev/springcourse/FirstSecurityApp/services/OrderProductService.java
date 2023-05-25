package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.entity.OrderProduct;
import ru.alishev.springcourse.FirstSecurityApp.repositories.OrderProductRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public List<OrderProduct> getOrderProduct(int id){
        return orderProductRepository.findByOrderId(id);
    }
}
