package sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdj.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
}
