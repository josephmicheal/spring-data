package sdj.entity;

import java.time.LocalDate;

import javax.persistence.Column;
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

	@Column(unique=true)
    private String username;

    @Column(name="registration_date")
    private LocalDate registrationDate;

    private String email;

    private Integer level;
    
    @Column(name="active")
    private Boolean isActive;

    public User() {}    
	public User(String username, LocalDate registrationDate, String email, Integer level, Boolean isActive) {
		this.username = username;
		this.registrationDate = registrationDate;
		this.email = email;
		this.level = level;
		this.isActive = isActive;
	}
	
	public String getUsername() {
		return username;
	}
	public Integer getLevel() {
		return level;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", registrationDate=" + registrationDate + ", email="
				+ email + ", level=" + level + ", isActive=" + isActive + "]";
	}

}
