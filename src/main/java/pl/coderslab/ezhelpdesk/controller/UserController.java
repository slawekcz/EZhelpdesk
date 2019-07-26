package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.ezhelpdesk.entity.User;
import pl.coderslab.ezhelpdesk.repository.UserRepository;
import pl.coderslab.ezhelpdesk.service.EmailService;
import pl.coderslab.ezhelpdesk.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserRepository userRepository;
    private final UserService userService;
    private EmailService emailService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, EmailService emailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String create(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/register";
        }
        userService.saveUser(user);
        emailService.sendConfirmationEmail(user.getEmail(), "You have registered, please confirm email address");
        return "redirect:/ticket/index";
    }

    @GetMapping("admin/users")
    public String registeredUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("admin/user/details/{userId}")
    public String userDetails(@PathVariable(name = "userId") Long id, Model model) {
        model.addAttribute("user",userRepository.findFirstById(id));
        return  "admin/user_details";
    }

    @GetMapping("/admin/user/block/{userId}")
    public String toggleEnableUser(@RequestParam(name = "action", required = false) String action, @PathVariable(name = "userId") Long id) {
        User user = userRepository.findFirstById(id);
        if ("disable".equals(action)) {
            user.setEnabled(0);
        } else if ("enable".equals(action)) {
            user.setEnabled(1);
        }
        userRepository.save(user);
        return "redirect:/admin/user/details/" + id;
    }

    @GetMapping("/admin/user/{userId}/edit")
    public String editUser(@PathVariable(name="userId") Long id, Model model) {
        model.addAttribute("user", userRepository.findFirstById(id));
        return "admin/edit_user";
    }

    @PostMapping("/admin/user/edit")
    public String editUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit_user";
        }
        userService.editUser(user);
        return "redirect:/admin/users";
    }
}
