package ru.shiryaeva.projectjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shiryaeva.projectjava.model.Order;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByItems_Product_Id(Long productId);

}
