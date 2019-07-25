package pl.coderslab.ezhelpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.ezhelpdesk.entity.Ticket;
import pl.coderslab.ezhelpdesk.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findFirstById(Long id) {
        return ticketRepository.findFirstById(id);
    }

    public List<Ticket> findAllByUserId(Long id) {
        return ticketRepository.findAllByUserId(id);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public List<Ticket> findAllByTitleLike(String query) {
        return ticketRepository.findAllByTitleLike(query);
    }

    public List<Ticket> findByCategory(Long id) {
        return ticketRepository.findAllByCategoryId(id);
    }
}
