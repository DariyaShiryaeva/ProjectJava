package ru.shiryaeva.projectjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.shiryaeva.projectjava.model.*;
import ru.shiryaeva.projectjava.service.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {

  private final UserService userService;
  private final ProductService productService;
  private final OrderService orderService;

  public DataLoader(UserService userService,
      ProductService productService,
      OrderService orderService) {
    this.userService = userService;
    this.productService = productService;
    this.orderService = orderService;
  }

  @Override
  public void run(String... args) {
    // USERS
    Map<Integer, User> users = new HashMap<>();
    users.put(1, createUser("Анна", "Сергеевна", "Иванова", "анна.иванов@mail.ru"));
    users.put(2, createUser("Михаил", "Андреевич", "Смирнов", "михаил.смирнов@mail.ru"));
    users.put(3, createUser("Елена", "Викторовна", "Кузнецова", "елена.кузнецов@mail.ru"));
    users.put(4, createUser("Дмитрий", "Ильич", "Попов", "дмитрий.попов@mail.ru"));
    users.put(5, createUser("Ольга", "Павловна", "Соколова", "ольга.соколов@mail.ru"));
    users.put(6, createUser("Иван", "Олегович", "Лебедев", "иван.лебедев@mail.ru"));
    users.put(7, createUser("Светлана", "Ивановна", "Козлова", "светлана.козлов@mail.ru"));
    users.put(8, createUser("Алексей", "Алексеевич", "Новиков", "алексей.новиков@mail.ru"));
    users.put(9, createUser("Мария", "Владимировна", "Морозова", "мария.морозов@mail.ru"));
    users.put(10, createUser("Павел", "Петрович", "Петров", "павел.петров@mail.ru"));

    // PRODUCTS
    Map<Integer, Product> products = new HashMap<>();
    products.put(1, createProduct("Футболка белая", 1299));
    products.put(2, createProduct("Футболка чёрная", 1399));
    products.put(3, createProduct("Джинсы синие", 2999));
    products.put(4, createProduct("Джинсы серые", 3199));
    products.put(5, createProduct("Куртка кожаная", 8999));
    products.put(6, createProduct("Платье красное", 4599));
    products.put(7, createProduct("Платье синее", 4299));
    products.put(8, createProduct("Кроссовки белые", 5999));
    products.put(9, createProduct("Кроссовки чёрные", 6199));
    products.put(10, createProduct("Куртка зимняя", 9999));

    // ORDERS
    createOrder(users.get(1), LocalDate.of(2025, 8, 24), new int[][]{{1, 2}, {7, 3}});
    createOrder(users.get(1), LocalDate.of(2025, 7, 25), new int[][]{{2, 1}, {4, 1}, {6, 3}});
    createOrder(users.get(2), LocalDate.of(2025, 9, 19), new int[][]{{3, 3}, {2, 2}, {6, 2}});
    createOrder(users.get(2), LocalDate.of(2025, 10, 9), new int[][]{{4, 2}, {10, 3}});
    createOrder(users.get(3), LocalDate.of(2025, 9, 24), new int[][]{{5, 1}, {4, 1}, {4, 3}});
    createOrder(users.get(3), LocalDate.of(2025, 10, 13), new int[][]{{6, 2}, {2, 3}, {7, 1}});
    createOrder(users.get(4), LocalDate.of(2025, 8, 5), new int[][]{{7, 1}, {8, 2}, {4, 3}, {9, 3}});
    createOrder(users.get(4), LocalDate.of(2025, 8, 9), new int[][]{{5, 2}, {4, 2}});
    createOrder(users.get(5), LocalDate.of(2025, 7, 8), new int[][]{{9, 1}, {5, 3}, {10, 2}});
    createOrder(users.get(5), LocalDate.of(2025, 8, 27), new int[][]{{5, 3}, {7, 3}, {8, 2}});
    createOrder(users.get(6), LocalDate.of(2025, 7, 2), new int[][]{{1, 2}, {7, 1}, {3, 2}});
    createOrder(users.get(6), LocalDate.of(2025, 10, 10), new int[][]{{2, 1}, {4, 1}, {1, 1}});
    createOrder(users.get(7), LocalDate.of(2025, 8, 3), new int[][]{{3, 2}, {10, 3}, {10, 1}});
    createOrder(users.get(7), LocalDate.of(2025, 8, 9), new int[][]{{4, 1}, {6, 2}, {2, 3}, {7, 2}});
    createOrder(users.get(8), LocalDate.of(2025, 7, 22), new int[][]{{5, 2}, {10, 2}});
    createOrder(users.get(8), LocalDate.of(2025, 8, 21), new int[][]{{6, 1}, {9, 1}, {9, 3}, {3, 2}});
    createOrder(users.get(8), LocalDate.of(2025, 9, 8), new int[][]{{1, 1}, {8, 2}, {10, 2}});
    createOrder(users.get(9), LocalDate.of(2025, 10, 20), new int[][]{{7, 3}, {1, 2}, {4, 3}, {1, 1}});
    createOrder(users.get(9), LocalDate.of(2025, 8, 8), new int[][]{{8, 2}, {2, 3}, {9, 1}, {5, 3}});
    createOrder(users.get(10), LocalDate.of(2025, 8, 9), new int[][]{{9, 1}, {6, 3}});
    createOrder(users.get(10), LocalDate.of(2025, 9, 13), new int[][]{{10, 2}, {5, 2}, {6, 2}});

    System.out.println("DataLoader: Все данные загружены");
  }

  private User createUser(String firstName, String middleName, String lastName, String email) {
    User user = new User();
    user.setFirstName(firstName);
    user.setMiddleName(middleName);
    user.setLastName(lastName);
    user.setEmail(email);
    userService.save(user);
    return user;
  }

  private Product createProduct(String name, double price) {
    Product product = new Product();
    product.setName(name);
    product.setPrice(price);
    productService.save(product);
    return product;
  }

  private void createOrder(User user, LocalDate orderDate, int[][] productsWithQuantity) {
    Order order = new Order();
    order.setUser(user);
    order.setOrderDate(orderDate);

    for (int[] pq : productsWithQuantity) {
      int productId = pq[0];
      int quantity = pq[1];

      if (quantity <= 0) continue;
      Product product = productService.findById((long) productId);
      if (product != null) {
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(product.getPrice() * quantity);
        order.addItem(item);
      }
    }

    orderService.save(order);
  }
}
