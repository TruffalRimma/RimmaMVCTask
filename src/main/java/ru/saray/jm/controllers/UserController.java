package ru.saray.jm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.saray.jm.model.User;
import ru.saray.jm.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String showUsers(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserByID(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.save(user);
        return "redirect:/users";
    }

    // страница для редактирования человека - возвращает html-страницу для редактирования юзера
    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserByID(id));
        return "update";
    }

    // реализация метода контроллера, который принимает PATCH - запрос
    // на адрес /users/id. Получаем человека по id - меняем его значения, который пришли в объект User
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.update(user);
        return "redirect:/users";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserByID(id);
        userService.delete(user);
        return "redirect:/users";
    }
}
