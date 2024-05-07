package sdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sdj.entity.Ticket;
import sdj.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	//@Transactional
	public void bookTicket(Ticket ticket) {
		ticketRepository.save(ticket);
		throw new RuntimeException("Payment failed!");
	}

}
