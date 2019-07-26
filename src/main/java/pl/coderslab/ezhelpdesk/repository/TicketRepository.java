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

    public List<Ticket> findAllByCategoryId(Long id);

    @Query("select t from Ticket t where t.title like %?1%")
    public List<Ticket> findAllByTitleLike(String query);


    @Query(value="select * from ticket t join user u on t.user_id = u.id where t.title like %?1% " +
            "or u.username like %?1%", nativeQuery = true )
    public List<Ticket> findByTitleOrUserName(String query);

}
