package ru.shiryaeva.projectjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shiryaeva.projectjava.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
