package ru.shiryaeva.projectjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // автоматически генерируемый ID
  private Long id;

  @NotBlank
  private String name;

  private String category;
  private String size;
  private String color;

  @Min(1)
  private double price;

  private int stockQuantity;
  private double rating;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getCategory() { return category; }
  public void setCategory(String category) { this.category = category; }

  public String getSize() { return size; }
  public void setSize(String size) { this.size = size; }

  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }

  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }

  public int getStockQuantity() { return stockQuantity; }
  public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

  public double getRating() { return rating; }
  public void setRating(double rating) { this.rating = rating; }
}
