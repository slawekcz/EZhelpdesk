package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.ezhelpdesk.entity.Comment;
import pl.coderslab.ezhelpdesk.entity.Ticket;
import pl.coderslab.ezhelpdesk.repository.CommentRepository;
import pl.coderslab.ezhelpdesk.repository.TicketRepository;
import pl.coderslab.ezhelpdesk.service.CommentService;
import pl.coderslab.ezhelpdesk.service.EmailService;

@Controller
public class CommentController {
    private CommentService commentService;
    private TicketRepository ticketRepository;
    private EmailService emailService;

    @Autowired
    public CommentController(CommentRepository commentRepository, CommentService commentService, TicketRepository ticketRepository, EmailService emailService) {
        this.commentService = commentService;
        this.ticketRepository = ticketRepository;
        this.emailService = emailService;
    }

    @PostMapping("ticket/{ticketId}/comment")
    public String comment(@PathVariable(name = "ticketId") Long id, @ModelAttribute Comment comment) {
        commentService.save(comment);
        Ticket ticket = ticketRepository.findFirstById(comment.getTicket().getId());
        String ticektOwnerEmail = ticket.getUser().getEmail();
        emailService.sendConfirmationEmail(ticektOwnerEmail, "Your ticket was commented");
        return "redirect:/ticket/" + id;
    }

    @GetMapping("/ticket/{tId}/comment/{id}/delete")
    public String deleteComment(@PathVariable(name = "tId") Long tId, @PathVariable(name="id") Long id) {
        commentService.deleteComment(id);
        return "redirect:/ticket/" + tId;
    }
}
