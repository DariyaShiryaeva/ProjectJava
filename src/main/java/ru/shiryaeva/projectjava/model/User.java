package ru.shiryaeva.projectjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Имя не может быть пустым")
  @Size(min = 2, max = 50)
  private String firstName;

  private String middleName;

  @NotBlank(message = "Фамилия не может быть пустой")
  @Size(min = 2, max = 50)
  private String lastName;

  @Email
  @NotBlank
  private String email;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name; // новое поле для полного имени

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Order> orders = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
    updateName();
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
    updateName();
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
    updateName();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    if (middleName != null && !middleName.isEmpty()) {
      return firstName + " " + middleName + " " + lastName;
    } else {
      return firstName + " " + lastName;
    }
  }

  public void setFullName(String fullName) {
    if (fullName == null || fullName.isBlank()) return;

    String[] parts = fullName.trim().split("\\s+");

    if (parts.length == 3) {
      this.firstName = parts[0];
      this.middleName = parts[1];
      this.lastName = parts[2];
    } else if (parts.length == 2) {
      this.firstName = parts[0];
      this.middleName = "";
      this.lastName = parts[1];
    } else {
      this.firstName = fullName;
      this.middleName = "";
      this.lastName = "";
    }
    updateName();
  }

  private void updateName() {
    this.name = getFullName();
  }

  public List<Order> getOrders() {
    return orders;
  }

}
