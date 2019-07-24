package pl.coderslab.ezhelpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.ezhelpdesk.entity.Comment;
import pl.coderslab.ezhelpdesk.repository.CommentRepository;

@Controller
public class CommentController {
    CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("ticket/{ticketId}/comment")
    public String comment(@PathVariable(name = "ticketId") Long id, @ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return "redirect:/ticket/" + id;
    }
}
