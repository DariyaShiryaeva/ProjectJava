package ru.shiryaeva.projectjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shiryaeva.projectjava.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}
