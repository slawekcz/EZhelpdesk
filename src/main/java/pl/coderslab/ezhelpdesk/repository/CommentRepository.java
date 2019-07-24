package pl.coderslab.ezhelpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.ezhelpdesk.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
