package pl.coderslab.ezhelpdesk.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne
    private Ticket ticket;
    @ManyToOne
    private User user;
}
