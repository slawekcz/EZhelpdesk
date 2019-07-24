package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.ezhelpdesk.entity.User;
import pl.coderslab.ezhelpdesk.repository.UserRepository;
import pl.coderslab.ezhelpdesk.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/create")
    public String create(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/register";
        }
        userService.saveUser(user);
        return "redirect:/ticket/index";
    }

    @GetMapping("/index")
    public String registeredUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setFirstName("Tom");
        user.setLastName("Wolski");
        user.setEmail("security@xxb.ccc");
        userService.saveUser(user);
        return "admin";
    }
}
