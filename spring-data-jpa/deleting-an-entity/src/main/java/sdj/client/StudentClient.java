package sdj.client;

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
		  Student returnedStudent = studentRepository.save(student);

		  //deleting an entity
		  studentRepository.delete(returnedStudent);		
		  
		  //checking whether an entity with the given id exists or not
		  boolean isFound = studentRepository.existsById(1L);
		  
		  if(isFound) {
			  studentRepository.deleteById(1L);
		  }
		  
		  
	}	

}