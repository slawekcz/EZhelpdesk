package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.ezhelpdesk.entity.Category;
import pl.coderslab.ezhelpdesk.entity.Ticket;
import pl.coderslab.ezhelpdesk.entity.User;
import pl.coderslab.ezhelpdesk.repository.CategoryRepository;
import pl.coderslab.ezhelpdesk.repository.TicketRepository;
import pl.coderslab.ezhelpdesk.repository.UserRepository;
import pl.coderslab.ezhelpdesk.service.CurrentUser;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    TicketRepository ticketRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository, UserRepository userRepository,
                            CategoryRepository categoryRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/index")
    public String index(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        model.addAttribute("title", "Tickets");
        User currentUser = customUser.getUser();
        model.addAttribute("currentUser", currentUser);
        return "ticket/index";
    }

    @GetMapping("/{ticketId}")
    public String index(@PathVariable(name = "ticketId") Long id, Model model) {
        Ticket ticket = ticketRepository.findFirstById(id);
        model.addAttribute("ticket", ticket);
        return "ticket/details";
    }

    @GetMapping("/user/{userId}")
    public String userTickets(@PathVariable(name = "userId") Long id, Model model) {
        List<Ticket> tickets = ticketRepository.findAllByUserId(id);
        model.addAttribute("tickets", tickets);
        return "ticket/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("user", userRepository.findFirstById(1L) );
        return "ticket/create";
    }

    @PostMapping("/create")
    public String create(@Valid Ticket ticket, BindingResult result) {
        if (result.hasErrors()) {
            return "ticket/create";
        }
        ticket.setStatus("open");
        ticketRepository.save(ticket);
        return "redirect:/ticket/index";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }
}
