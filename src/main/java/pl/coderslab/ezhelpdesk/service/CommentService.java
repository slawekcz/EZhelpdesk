package pl.coderslab.ezhelpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.ezhelpdesk.entity.Comment;
import pl.coderslab.ezhelpdesk.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
