package sdj.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import sdj.entity.Student;
import sdj.repository.StudentRepository;

@Component
public class StudentClient implements ApplicationRunner {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {			
		Student student = new Student("Alisa Simmons", "2022AN50123"); 
		studentRepository.save(student);
		
		Optional<Student> optional = studentRepository.findById(1L);
		Student studentById = optional.get();
		System.out.println(studentById);	
		
		Student student1 = studentRepository.getById(1L);
		System.out.println(student1);
		
		Student student2 = studentRepository.getOne(1L);
		System.out.println(student2);
	}	

}