package pl.coderslab.ezhelpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.ezhelpdesk.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findFirstById(Long id);

    User findByUsername(String username);
}
