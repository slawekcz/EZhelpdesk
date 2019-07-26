package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.ezhelpdesk.entity.Category;
import pl.coderslab.ezhelpdesk.entity.Comment;
import pl.coderslab.ezhelpdesk.entity.Ticket;
import pl.coderslab.ezhelpdesk.entity.User;
import pl.coderslab.ezhelpdesk.repository.UserRepository;
import pl.coderslab.ezhelpdesk.service.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("currentUser")
public class TicketController {
    private FileService fileService;
    private UserService userService;
    private TicketService ticketService;
    private CategoryService categoryService;

    @Autowired
    public TicketController(FileService fileService, UserService userService,
                            TicketService ticketService, CategoryService categoryService, UserRepository userRepository) {
        this.fileService = fileService;
        this.userService = userService;
        this.ticketService = ticketService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/ticket/index"})
    public String index(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        List<Ticket> tickets = ticketService.findAll();

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
        Ticket ticket = ticketService.findFirstById(id);
        model.addAttribute("ticket", ticket);
        model.addAttribute("comment", new Comment());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("files", fileService.findAllByTicketId(id));
        return "ticket/details";
    }

    @GetMapping("/ticket/user/{userId}")
    public String userTickets(@PathVariable(name = "userId") Long id, Model model) {
        List<Ticket> tickets = ticketService.findAllByUserId(id);
        model.addAttribute("tickets", tickets);
        return "ticket/index";
    }

    @GetMapping("/ticket/create")
    public String create(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("ticket", new Ticket());
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", userService.findFirstById(currentUser.getUser().getId()));
        return "ticket/create";
    }

    @PostMapping("/ticket/create")
    public String create(@Valid Ticket ticket, BindingResult result,
                         @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "ticket/create";
        }
        ticket.setStatus("open");
        ticketService.save(ticket);
        return "redirect:/ticket/index";
    }

    @GetMapping("ticket/{ticketId}/close")
    public String close(@PathVariable(name = "ticketId") Long id) {
        Ticket ticket = ticketService.findFirstById(id);
        ticket.setStatus("closed");
        ticketService.save(ticket);
        return "redirect:/ticket/index";
    }

    @GetMapping("ticket/search")
    public String search(@RequestParam(name = "query") String query, Model model) {
//        model.addAttribute("tickets", ticketService.findAllByTitleLike(query));
        model.addAttribute("tickets", ticketService.findByNameOrUser(query));
        return "ticket/index";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }
}
