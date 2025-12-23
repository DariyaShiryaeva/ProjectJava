package ru.shiryaeva.projectjava.service;

import org.springframework.stereotype.Service;
import ru.shiryaeva.projectjava.model.User;
import ru.shiryaeva.projectjava.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public void save(User user) {
    repository.save(user);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
