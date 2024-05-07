package sdj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sdj.entity.Student;
import sdj.repository.MyCustomRepository;

@SpringBootTest
public class MainApplicationTests {
	
	@Autowired
	private MyCustomRepository myCustomRepository;

	@Test
	public void testMyCustomRepository() {
		//save
		Student student = new Student("Alisa Simmons", "2022AN50123");
		Student returnedStudent = myCustomRepository.save(student);			
		assertNotNull(returnedStudent.getId());
		
		//findById
		Student foundStudent = myCustomRepository.findById(1L).get();
		assertEquals(1L, foundStudent.getId());
		
		//findAll
		List<Student> returnedStudents = myCustomRepository.findAll();
		assertEquals(1, returnedStudents.size());	
	}

}
