package pl.coderslab.ezhelpdesk.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.ezhelpdesk.entity.User;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String login(Model model) {
        return "user/signin";
    }

    @PostMapping("/login")
    public String create(@RequestParam String login, @RequestParam String password) {

        return "user/signin";
    }
}
