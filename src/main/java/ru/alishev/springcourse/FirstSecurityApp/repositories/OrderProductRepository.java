package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alishev.springcourse.FirstSecurityApp.entity.Order;
import ru.alishev.springcourse.FirstSecurityApp.entity.OrderProduct;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.OrderProductId;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findAllById(OrderProductId id);
}
