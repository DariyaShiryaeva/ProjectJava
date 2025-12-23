package ru.shiryaeva.projectjava.service;

import org.springframework.stereotype.Service;
import ru.shiryaeva.projectjava.model.OrderItem;
import ru.shiryaeva.projectjava.repository.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService {

  private final OrderItemRepository repository;

  public OrderItemService(OrderItemRepository repository) {
    this.repository = repository;
  }

  public List<OrderItem> findAll() {
    return repository.findAll();
  }

  public void save(OrderItem item) {
    repository.save(item);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
