package sdj;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sdj.entity.Ticket;
import sdj.repository.TicketRepository;
import sdj.service.TicketService;

@SpringBootTest
public class BookTicketTest {
	
	@Autowired
	private TicketService ticketSerivce;
	
	@Autowired
	private TicketRepository ticketRepository;

    @Test
    public void testBookingATicket() {
        try {
    		Ticket ticket = new Ticket("Bus Stop 1", "Bus Stop 2", LocalDateTime.of(2022, Month.MAY, 03, 14, 00));
    		ticketSerivce.bookTicket(ticket);
        } catch (Exception e) {
            System.out.println("====> " + e.getMessage());
        }
        finally {
            assertThat(ticketRepository.findAll()).isNotEmpty();
        }
    }

}