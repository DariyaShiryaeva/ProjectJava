package ru.shiryaeva.projectjava.service;

import ru.shiryaeva.projectjava.model.Product;
import ru.shiryaeva.projectjava.repository.ProductRepository;
import ru.shiryaeva.projectjava.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shiryaeva.projectjava.model.Order;

import java.util.List;

@Service
public class ProductService {

  private final ProductRepository repository;

  private final OrderRepository orderRepository;

  public ProductService(ProductRepository repository, OrderRepository orderRepository) {
    this.repository = repository;
    this.orderRepository = orderRepository;
  }

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Product findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public void save(Product product) {
    repository.save(product);
  }

  @Transactional
  public void delete(Long productId) {

    List<Order> orders = orderRepository.findByItems_Product_Id(productId);

    repository.deleteById(productId);

    for (Order order : orders) {
      if (order.getItems().isEmpty()) {
        orderRepository.delete(order);
      }
    }
  }

}
