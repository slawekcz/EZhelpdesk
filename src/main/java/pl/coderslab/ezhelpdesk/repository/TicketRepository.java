package pl.coderslab.ezhelpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.ezhelpdesk.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public List<Ticket> findAllByUserId(Long id);

    public Ticket findFirstById(Long id);

    @Query("select t from Ticket t where t.title like %?1%")
    public List<Ticket> findAllByTitleLike(String query);

}
