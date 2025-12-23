package ru.shiryaeva.projectjava.controller;

import ru.shiryaeva.projectjava.model.User;
import ru.shiryaeva.projectjava.service.UserService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("users", service.findAll());
    return "users/list";
  }

  @GetMapping("/new")
  public String create(Model model) {
    model.addAttribute("user", new User());
    return "users/form";
  }

  @PostMapping
  public String save(@Valid User user, BindingResult result) {
    if (result.hasErrors()) {
      return "users/form";
    }
    service.save(user);
    return "redirect:/users";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    service.delete(id);
    return "redirect:/users";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Long id, Model model) {
    User user = service.findById(id);
    if (user == null) {
      return "redirect:/users";
    }
    model.addAttribute("user", user);
    return "users/form";
  }
}
