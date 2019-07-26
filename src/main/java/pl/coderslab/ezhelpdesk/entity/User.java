package pl.coderslab.ezhelpdesk.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email(message = "enter valid email")
    private String email;
    @Size(min = 3, message = "password must not be empty")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private int enabled;
    private boolean isAdmin;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Comment> comments;

    @CreationTimestamp
    private LocalDateTime created;

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
