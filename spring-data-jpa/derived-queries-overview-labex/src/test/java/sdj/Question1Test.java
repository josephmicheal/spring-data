package sdj;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sdj.entity.Student;
import sdj.repository.StudentRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Question1Test {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testFindByEnrollmentId() {
		Student student = studentRepository.findByEnrollmentId("2007TE50698");
		System.out.println( student );
	}

	@BeforeAll
	public void populateDb() {
		Student student1 = new Student("Alissa Simmons", "2022AN50123");
		Student student2 = new Student("Tora Bialek", "2007TE50698");
		Student student3 = new Student("Sharon Ambriz", "2021SI50213");

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);
	}

	@AfterAll
	public void dePopulateDb() {
		studentRepository.deleteAll();
	}

}
