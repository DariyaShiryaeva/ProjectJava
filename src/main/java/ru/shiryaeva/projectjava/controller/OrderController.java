package ru.shiryaeva.projectjava.controller;

import ru.shiryaeva.projectjava.model.User;
import ru.shiryaeva.projectjava.model.OrderItem;
import ru.shiryaeva.projectjava.model.Order;
import ru.shiryaeva.projectjava.model.Product;
import ru.shiryaeva.projectjava.service.OrderService;
import ru.shiryaeva.projectjava.service.ProductService;
import ru.shiryaeva.projectjava.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Iterator;

@Controller
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;
  private final UserService userService;
  private final ProductService productService;

  public OrderController(OrderService orderService,
      UserService userService,
      ProductService productService) {
    this.orderService = orderService;
    this.userService = userService;
    this.productService = productService;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("orders", orderService.findAll());
    return "orders/list";
  }

  @PostMapping
  public String save(@ModelAttribute Order order) {
    order.getItems().removeIf(item -> item.getProduct() == null || item.getQuantity() <= 0);

    if (order.getItems().isEmpty()) {
      return "redirect:/orders/new";
    }

    User user = userService.findById(order.getUser().getId());
    order.setUser(user);

    for (OrderItem item : order.getItems()) {
      Product product = productService.findById(item.getProduct().getId());
      item.setProduct(product);
      item.setOrder(order);
      item.setPrice(product.getPrice() * item.getQuantity());
    }

    order.recalculateTotalAmount();
    orderService.save(order);

    return "redirect:/orders";
  }

  @GetMapping("/new")
  public String create(Model model) {
    Order order = new Order();
    order.setOrderDate(LocalDate.now());
    order.ensureItems(); // хотя бы один элемент для формы

    model.addAttribute("order", order);
    model.addAttribute("users", userService.findAll());
    model.addAttribute("products", productService.findAll());
    return "orders/form";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Long id, Model model) {
    Order order = orderService.findById(id);
    if (order == null) {
      return "redirect:/orders";
    }
    order.ensureItems(); // гарантируем хотя бы один элемент
    model.addAttribute("order", order);
    model.addAttribute("users", userService.findAll());
    model.addAttribute("products", productService.findAll());
    return "orders/form";
  }


  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    orderService.delete(id);
    return "redirect:/orders";
  }
}
