package pl.coderslab.ezhelpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.ezhelpdesk.entity.File;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    public List<File> findAllByTicketId(Long id);
}
