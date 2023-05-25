package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alishev.springcourse.FirstSecurityApp.entity.Order;
import ru.alishev.springcourse.FirstSecurityApp.entity.OrderProduct;
import ru.alishev.springcourse.FirstSecurityApp.entity.Product;
import ru.alishev.springcourse.FirstSecurityApp.entity.entityId.OrderProductId;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findAllById(OrderProductId id);

    @Query("SELECT op FROM OrderProduct op WHERE op.id.order.id = :orderId")
    List<OrderProduct> findByOrderId(int orderId);
}
