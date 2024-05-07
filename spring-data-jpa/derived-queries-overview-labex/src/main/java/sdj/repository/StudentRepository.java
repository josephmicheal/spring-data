package sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sdj.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("select s from Student s where s.enrollmentId = '2022AN50123' ")
	Student findByEnrollmentId(String enrollmentId);
	
}
