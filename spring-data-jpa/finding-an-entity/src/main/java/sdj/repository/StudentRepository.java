package sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdj.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
