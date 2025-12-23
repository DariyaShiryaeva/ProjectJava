package ru.shiryaeva.projectjava.service;

import ru.shiryaeva.projectjava.model.Order;
import ru.shiryaeva.projectjava.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

  private final OrderRepository repository;

  public OrderService(OrderRepository repository) {
    this.repository = repository;
  }

  public List<Order> findAll() {
    return repository.findAll();
  }

  public Order findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public void save(Order order) {
    repository.save(order);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
