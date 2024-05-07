package sdj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sdj.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByEnrollmentId(String enrollmentId);
	
	List<Student> findByEnrollmentIdStartingWithAndNameLike(String enrollmentId, String name);
	
	List<Student> findFirst2ByEnrollmentIdStartingWithAndNameLike(String enrollmentId, String name);
	
	//@Query("select s from Student s where s.name = :name")
	List<Student> findByName(String name);
	
	//@Query(value="select * from student where name like %?", nativeQuery = true)
	List<Student> findByNameEndingWith(String name);
	
}
