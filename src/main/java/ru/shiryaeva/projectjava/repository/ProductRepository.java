package ru.shiryaeva.projectjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shiryaeva.projectjava.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
