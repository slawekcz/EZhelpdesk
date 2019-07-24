package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.ezhelpdesk.entity.Category;
import pl.coderslab.ezhelpdesk.entity.Comment;
import pl.coderslab.ezhelpdesk.entity.Ticket;
import pl.coderslab.ezhelpdesk.entity.User;
import pl.coderslab.ezhelpdesk.repository.CategoryRepository;
import pl.coderslab.ezhelpdesk.repository.TicketRepository;
import pl.coderslab.ezhelpdesk.repository.UserRepository;
import pl.coderslab.ezhelpdesk.service.CurrentUser;

import javax.validation.Valid;
import java.util.List;

@Controller
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

    @GetMapping({"/ticket/index"})
    public String index(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        model.addAttribute("title", "Tickets");
        if (customUser != null) {
            User currentUser = customUser.getUser();
            model.addAttribute("currentUser", currentUser);
        }
        return "ticket/index";
    }

    @GetMapping("/ticket/{ticketId}")
    public String index(@PathVariable(name = "ticketId") Long id, Model model,
                        @AuthenticationPrincipal CurrentUser currentUser) {
        Ticket ticket = ticketRepository.findFirstById(id);
        model.addAttribute("ticket", ticket);
        model.addAttribute("comment", new Comment());
        model.addAttribute("currentUser", currentUser);
        return "ticket/details";
    }

    @GetMapping("/ticket/user/{userId}")
    public String userTickets(@PathVariable(name = "userId") Long id, Model model) {
        List<Ticket> tickets = ticketRepository.findAllByUserId(id);
        model.addAttribute("tickets", tickets);
        return "ticket/index";
    }

    @GetMapping("/ticket/create")
    public String create(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("user", userRepository.findFirstById(currentUser.getUser().getId()));
        return "ticket/create";
    }

    @PostMapping("/ticket/create")
    public String create(@Valid Ticket ticket, @AuthenticationPrincipal CurrentUser currentUser,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "ticket/create";
        }
        ticket.setStatus("open");
        ticketRepository.save(ticket);
        return "redirect:/ticket/index";
    }

    @GetMapping("ticket/{ticketId}/close")
    public String close(@PathVariable(name = "ticketId") Long id) {
        Ticket ticket = ticketRepository.findFirstById(id);
        ticket.setStatus("closed");
        ticketRepository.save(ticket);
        return "redirect:/ticket/index";
    }

    @GetMapping("ticket/search")
    public String search(@RequestParam(name = "query") String query, Model model) {
        model.addAttribute("tickets", ticketRepository.findAllByTitleLike(query));
        return "ticket/index";
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
