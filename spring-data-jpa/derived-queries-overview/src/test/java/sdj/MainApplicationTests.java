package sdj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
public class MainApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testFindByEnrollmentId() {
		Student student = studentRepository.findByEnrollmentId("2021SI50213");
		assertEquals("2021SI50213", student.getEnrollmentId());
		System.out.println(student);
	}

	@Test
	public void testFindByEnrollmentIdStartingWithAndNameLike() {
		List<Student> students = studentRepository.findByEnrollmentIdStartingWithAndNameLike("2022", "A%");
		assertTrue(students.size() == 3);
		System.out.println(students);
	}

	@Test
	public void testFindFirst2ByEnrollmentIdStartingWithAndNameLike() {
		List<Student> students = studentRepository.findFirst2ByEnrollmentIdStartingWithAndNameLike("2022", "A%");
		assertTrue(students.size() == 2);
		System.out.println(students);
	}

	@Test
	public void testFindByName() {
		List<Student> students = studentRepository.findByName("Alissa Simmons");
		assertEquals("Alissa Simmons", students.get(0).getName());
		System.out.println(students);
	}

	@Test
	public void testFindByNameEndingWith() {
		List<Student> students = studentRepository.findByNameEndingWith("Simmons");
		assertEquals("Alissa Simmons", students.get(0).getName());
		System.out.println(students);
	}

	@BeforeAll
	public void populateDb() {
		Student student1 = new Student("Alissa Simmons", "2022AN50123");
		Student student2 = new Student("Tora Bialek", "2007TE50698");
		Student student3 = new Student("Sharon Ambriz", "2021SI50213");
		Student student4 = new Student("Tianna Armentrout", "2022TU50471");
		Student student5 = new Student("Angelica Zapien", "2022AE50802");
		Student student6 = new Student("Aarti Evatt", "2022AT50385");

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);
		studentRepository.save(student4);
		studentRepository.save(student5);
		studentRepository.save(student6);
	}

	@AfterAll
	public void dePopulateDb() {
		studentRepository.deleteAll();
	}

}
