package pl.coderslab.ezhelpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.ezhelpdesk.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findFirstById(Long id);
}
