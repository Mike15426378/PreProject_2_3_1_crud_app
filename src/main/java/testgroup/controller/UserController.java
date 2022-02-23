package testgroup.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testgroup.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import testgroup.SERVICE.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String allUsers(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "listUsers";
    }

    //страница для редактирования
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable(value = "id", required = true) int id, Model model) {
        model.addAttribute("user", userService.readById(id));
        return "edit";
    }
    //метод редактирования пользователя
    @PatchMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/";
    }

    //получаем странцу для добавления
    @GetMapping("/add")
    public String addPage(@ModelAttribute("user") User user) {
        return "add";
    }
    //метод для добавления
    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    //удаление пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
