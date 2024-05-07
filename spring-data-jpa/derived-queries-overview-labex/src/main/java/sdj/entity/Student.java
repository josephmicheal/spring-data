package sdj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Student.findByEnrollmentId",
						 query = "select s from Student s where s.enrollmentId = '2021SI50213' ")
public class Student {

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnrollmentId() {
		return enrollmentId;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", enrollmentId=" + enrollmentId + "]";
	}
	
}
