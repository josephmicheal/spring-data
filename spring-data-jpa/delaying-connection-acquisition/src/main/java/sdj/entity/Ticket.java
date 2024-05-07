package sdj.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
	
    private String origin;
    private String destination;
    
    @Column(name="scheduled_at")
    private LocalDateTime scheduledAt;

	public Ticket() {}
	public Ticket(String origin, String destination, LocalDateTime scheduledAt) {
		this.origin = origin;
		this.destination = destination;
		this.scheduledAt = scheduledAt;
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", origin=" + origin + ", destination=" + destination + ", scheduledAt="
				+ scheduledAt + "]";
	}	
	
}
