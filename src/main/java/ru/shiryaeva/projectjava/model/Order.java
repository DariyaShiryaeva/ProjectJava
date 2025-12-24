package ru.shiryaeva.projectjava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate orderDate;

  private double totalAmount;

  @ManyToOne
  private User user;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  // ======= Геттеры и сеттеры =======
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public LocalDate getOrderDate() { return orderDate; }
  public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

  public double getTotalAmount() { return totalAmount; }

  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }

  public List<OrderItem> getItems() { return items; }
  public void setItems(List<OrderItem> items) { this.items = items; }

  public void addItem(OrderItem item) {
    item.setOrder(this);
    this.items.add(item);
    recalculateTotalAmount();
  }

  public void removeItem(OrderItem item) {
    this.items.remove(item);
    item.setOrder(null);
    recalculateTotalAmount();
  }

  public void recalculateTotalAmount() {
    this.totalAmount = items.stream()
        .mapToDouble(OrderItem::getPrice)
        .sum();
  }

  public void ensureItems() {
    if (items.isEmpty()) {
      addItem(new OrderItem());
    }
  }
}

