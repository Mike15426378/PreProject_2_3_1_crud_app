package testgroup.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import testgroup.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import testgroup.SERVICE.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();               //достаем все пользователя из бд
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");                       //имя файла .jsp
        modelAndView.addObject("usersList", users);  //-> данный атрибут передаем в файл users.jsp
        return modelAndView;
    }

    //метод получения страницы редактирования
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.readById(id);                    //достаем из бд пользователя
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");                   //имя файла .jsp
        modelAndView.addObject("user", user);       //передаем атрибут в файл .jsp и в след метод ->
        return modelAndView;
    }

    // -> метод редактирования пользователя
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");                  //переход: на стартовую страницу
        userService.edit(user);                                  //добавили пользователя в бд*
        return modelAndView;
    }

    //по адресу /add доступны два метода, но у них разные запросы
    //добавление новых пользователей, но сначала получаем странцу
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    //сам метод добавления пользователя
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.add(user);                                     //добавили в бд пользователя
        return modelAndView;
    }

    //удаление пользователя
//    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteUser(@PathVariable("id") int id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        User user = userService.readById(id);
//        userService.delete(user);
//        return modelAndView;
//    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.delete(id);

        return modelAndView;
    }
}
