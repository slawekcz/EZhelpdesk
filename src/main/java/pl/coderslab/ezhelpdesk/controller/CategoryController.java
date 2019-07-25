package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.ezhelpdesk.repository.CategoryRepository;
import pl.coderslab.ezhelpdesk.service.CategoryService;
import pl.coderslab.ezhelpdesk.service.TicketService;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    TicketService ticketService;


    @GetMapping("/categories")
    public  String categories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/index";
    }

    @GetMapping("/category/{id}")
    public String showTicketsBycategory(@PathVariable Long id, Model model) {
        model.addAttribute("tickets", ticketService.findByCategory(id));
        return "ticket/index";
    }
}
