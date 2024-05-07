package sdj.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(name="enrollment_id", nullable=false, unique=true)
	private String enrollmentId;

	public Student() {}
	public Student(String name, String enrollmentId) {
		this.name = name;
		this.enrollmentId = enrollmentId;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", enrollmentId=" + enrollmentId + "]";
	}
	
}
