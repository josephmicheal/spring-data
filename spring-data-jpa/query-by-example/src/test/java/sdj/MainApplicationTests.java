package sdj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import sdj.entity.User;
import sdj.repository.UserRepository;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT )
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testQueryByExample1() {
		User user = new User();
		user.setLastname("Smith");
		user.setLevel(2);
		
		Example<User> example = Example.of(user);
		List<User> matchingUsers = userRepository.findAll(example);
		System.out.println(matchingUsers);
		assertEquals(2, matchingUsers.size());
	}
	
	@Test
	public void testQueryByExample2() {
		User user = new User();
		user.setLastname("Smith");
		
		Example<User> example = Example.of(user);
		List<User> matchingUsers = userRepository.findAll(example);
		System.out.println(matchingUsers);
		assertEquals(0, matchingUsers.size());
	}
	
	@Test
	public void testQueryByExample3() {
		User user = new User();
		user.setLastname("Smith");
		
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("level");
		Example<User> example = Example.of(user, matcher);
		List<User> matchingUsers = userRepository.findAll(example);
		System.out.println(matchingUsers);
		assertEquals(3, matchingUsers.size());
	}
	
	@Test
	public void testQueryByExample4() {
		User user = new User();
		user.setFirstname("s");
		
		ExampleMatcher matcher = ExampleMatcher.matching()
															                 .withIgnorePaths("level")
															                 .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
															                 .withIgnoreCase();
		Example<User> example = Example.of(user, matcher);
		List<User> matchingUsers = userRepository.findAll(example);
		System.out.println(matchingUsers);
		assertEquals(2, matchingUsers.size());
	}
	
	@Test
	public void testQuestion1() {
		User user = new User();
		user.setFirstname("s");
		user.setEmail("@else.com");
		
		ExampleMatcher matcher = ExampleMatcher.matching()
															                 .withIgnorePaths("level")
															                 .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
															                 .withIgnoreCase();
		Example<User> example = Example.of(user, matcher);
		List<User> matchingUsers = userRepository.findAll(example);
		System.out.println(matchingUsers);
		assertEquals(0, matchingUsers.size());
	}
	
	@Test
	public void testQueryByExample5() {
		User user = new User();
		user.setFirstname("s");
		user.setEmail("@else.com");
		
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
															                 .withIgnorePaths("level")
															                 .withMatcher("firstname", match -> match.startsWith())
															                 .withMatcher("email", match -> match.endsWith())
															                 .withIgnoreCase();
		Example<User> example = Example.of(user, matcher);
		List<User> matchingUsers = userRepository.findAll(example);
		System.out.println(matchingUsers);
		assertEquals(1, matchingUsers.size());
	}
	
	@BeforeAll
	public void populateDb() {
		User user1 = new User("gillamy01", "Amy", "Gill", "amy@somewhrelse.com", 1);
		User user2 = new User("smithjohn02", "John", "Smith", "john@somewhr.com", 2);
		User user3 = new User("lawsonmike03", "Mike", "Lawson", "mike@else.com", 3);
		User user4 = new User("lambian03", "Ian", "Lamb", "ian@somewhr.com", 3);
		User user5 = new User("bailektora02", "Tora", "Bailek", "tora@somewhr.com", 2);
		User user6 = new User("smithsadie01", "Sadie", "Smith", "sadie@somewhrelse.com", 1);
		User user7 = new User("ambrizsharon01", "Sharon", "Ambriz", "sharon@else.com", 1);
		User user8 = new User("singhrahul02", "Rahul", "Singh", "rahul@somewhr.com", 2);
		User user9 = new User("smithjoe02", "Joe", "Smith", "joe@else.com", 2);
		User user10 = new User("johnsonleo03", "Leo", "Johnson", "leo@somewhr.com", 3);
		User user11 = new User("leebrett04", "Brett", "Lee", "brett@else.com", 4);
		
		userRepository.saveAll( List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11 ) );
	}

	@AfterAll
	public void dePopulateDb() {
		userRepository.deleteAll();
	}

}
