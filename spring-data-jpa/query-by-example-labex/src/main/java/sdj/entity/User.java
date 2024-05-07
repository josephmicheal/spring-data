package sdj.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private int level;    
    private boolean isActive;
    
	public User() {}
	public User(String username, String firstname, String lastname, String email, int level, boolean isActive) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.level = level;
		this.isActive = isActive;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", level=" + level + ", isActive=" + isActive + "]";
	}	
	
}
