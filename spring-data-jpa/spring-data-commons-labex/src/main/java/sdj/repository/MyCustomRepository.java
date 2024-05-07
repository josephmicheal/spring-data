package sdj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import sdj.entity.Student;

public interface MyCustomRepository extends Repository<Student, Long> {
	
	Student save(Student student);
	
	Optional<Student> findById(Long id);
	
	List<Student> findAll();

}
