package pl.coderslab.ezhelpdesk.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.ezhelpdesk.entity.Ticket;
import pl.coderslab.ezhelpdesk.repository.CategoryRepository;
import pl.coderslab.ezhelpdesk.repository.TicketRepository;
import pl.coderslab.ezhelpdesk.repository.UserRepository;

@Component
public class TicketFixture {
    TicketRepository ticketRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;

    @Autowired
    public TicketFixture(TicketRepository ticketRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public void initData() {
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();
        Ticket ticket4 = new Ticket();

        ticket1.setTitle("first ticket");
        ticket1.setStatus("open");
        ticket1.setPriority("low");
        ticket1.setText("he good thing is that Spring Security will do this for you. ... When a user goes to change passwords you hash the \"enter your old password\" to ... still matches, if it does you hash the \"enter your new password\" and save it.");
        ticket1.setCategory(categoryRepository.findFirstById(1L));
        ticket1.setUser(userRepository.findFirstById(11L));

        ticket2.setTitle("second ticket");
        ticket2.setStatus("open");
        ticket2.setText("he good thing is that Spring Security will do this for you. ... When a user goes to change passwords you hash the \"enter your old password\" to ... still matches, if it does you hash the \"enter your new password\" and save it.");
        ticket2.setPriority("low");
        ticket2.setCategory(categoryRepository.findFirstById(3L));
        ticket2.setUser(userRepository.findFirstById(11L));

        ticket3.setTitle("third ticket");
        ticket3.setStatus("open");
        ticket3.setPriority("low");
        ticket3.setText("he good thing is that Spring Security will do this for you. ... When a user goes to change passwords you hash the \"enter your old password\" to ... still matches, if it does you hash the \"enter your new password\" and save it.");
        ticket3.setCategory(categoryRepository.findFirstById(2L));
        ticket3.setUser(userRepository.findFirstById(7L));

        ticket4.setTitle("another ticket");
        ticket4.setStatus("closed");
        ticket4.setPriority("low");
        ticket4.setText("he good thing is that Spring Security will do this for you. ... When a user goes to change passwords you hash the \"enter your old password\" to ... still matches, if it does you hash the \"enter your new password\" and save it.");
        ticket4.setCategory(categoryRepository.findFirstById(2L));
        ticket4.setUser(userRepository.findFirstById(7L));

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
        ticketRepository.save(ticket3);
        ticketRepository.save(ticket4);
    }
}
