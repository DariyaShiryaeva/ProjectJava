package ru.shiryaeva.projectjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shiryaeva.projectjava.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
