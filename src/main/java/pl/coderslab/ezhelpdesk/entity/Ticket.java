package pl.coderslab.ezhelpdesk.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String text;

    private String status;
    @NotBlank
    private String priority;
    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "ticket")
    @ToString.Exclude
    private List<Comment> comment;

    @OneToMany(mappedBy = "ticket")
    @ToString.Exclude
    private List<File> files;

}
