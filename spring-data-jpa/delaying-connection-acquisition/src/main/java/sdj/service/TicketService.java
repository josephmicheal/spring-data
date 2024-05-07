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

    @Transactional
    public void doSomeTimeConsumingTask() throws InterruptedException {
    	System.out.println("Waiting for a time-consuming task that doesn't need a database connection ...");

    	//Using sleep of 40 seconds to capture HikariCP connection pool status, which take place at every 30 seconds. 
    	//This will show if the connection was opened (acquired from the connection pool) or not.
        Thread.sleep(40000);

        System.out.println("Time-consuming task done, now query the database to load Ticket[id=1L] ...");
        System.out.println("The database connection should be acquired now from HikariCP connection pool ...");
        Ticket ticket = ticketRepository.findById(1L).get();

        //At this point, the connection should be open.
        Thread.sleep(40000);
    }

}
