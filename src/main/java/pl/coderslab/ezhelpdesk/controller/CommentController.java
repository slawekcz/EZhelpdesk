package pl.coderslab.ezhelpdesk.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.ezhelpdesk.entity.Comment;
import pl.coderslab.ezhelpdesk.entity.Ticket;
import pl.coderslab.ezhelpdesk.repository.CommentRepository;
import pl.coderslab.ezhelpdesk.repository.TicketRepository;
import pl.coderslab.ezhelpdesk.service.EmailService;

@Controller
public class CommentController {
    private CommentRepository commentRepository;
    private TicketRepository ticketRepository;
    private EmailService emailService;

    @Autowired
    public CommentController(CommentRepository commentRepository, TicketRepository ticketRepository, EmailService emailService) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
        this.emailService = emailService;
    }

    @PostMapping("ticket/{ticketId}/comment")
    public String comment(@PathVariable(name = "ticketId") Long id, @ModelAttribute Comment comment) {
        commentRepository.save(comment);
        Ticket ticket = ticketRepository.findFirstById(comment.getTicket().getId());
        String ticektOwnerEmail = ticket.getUser().getEmail();
        emailService.sendConfirmationEmail(ticektOwnerEmail, "Your ticket was commented");
        return "redirect:/ticket/" + id;
    }
}
