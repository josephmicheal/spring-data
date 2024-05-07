package sdj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sdj.entity.Student;
import sdj.repository.StudentRepository;

@SpringBootTest
public class MainApplicationTests {
	
	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testCrud() {
		//Create
		Student student = new Student("Alisa Simmons", "2022AN50123");
		Student returnedStudent = studentRepository.save(student);			
		assertNotNull(returnedStudent.getId());
		
		//Update
		returnedStudent.setName("Alissa Simmons");
		Student updatedStudent = studentRepository.save(returnedStudent);		
		assertEquals("Alissa Simmons", updatedStudent.getName());
		
		//Read
		Student foundStudent = studentRepository.findById(1L).get();
		assertEquals(1L, foundStudent.getId());
		
		//Delete
		studentRepository.delete(foundStudent);
		assertFalse(studentRepository.existsById(1L));		
	}

}
