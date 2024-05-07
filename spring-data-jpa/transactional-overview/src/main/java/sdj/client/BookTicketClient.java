package sdj.client;

import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import sdj.entity.Ticket;
import sdj.service.TicketService;

//@Component
public class BookTicketClient implements ApplicationRunner {
	
	@Autowired
	private TicketService ticketService;

	@Override
	public void run(ApplicationArguments args) throws Exception {	
		Ticket ticket = new Ticket("Bus Stop 1", "Bus Stop 2", LocalDateTime.of(2022, Month.MAY, 03, 14, 00));
		ticketService.bookTicket(ticket);
	}

}