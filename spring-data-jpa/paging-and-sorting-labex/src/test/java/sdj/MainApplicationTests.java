package sdj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import sdj.entity.User;
import sdj.repository.UserRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testQuestion1Page1() {
		Page<User> page1Of3Users = userRepository.findByLevel(2, PageRequest.of(0, 3, Sort.by("registrationDate")));	
		List<User> users = page1Of3Users.getContent();
		System.out.println(users);
		
		assertEquals( 4, page1Of3Users.getTotalElements() );
		assertEquals( 3, page1Of3Users.getSize() );
		assertEquals( 2, page1Of3Users.getTotalPages() );
		assertEquals( true, page1Of3Users.hasNext() );
		assertEquals( 0, page1Of3Users.getNumber() );
		assertEquals( 3, users.size() );
		assertEquals( 3, page1Of3Users.getNumberOfElements() );
	}
	
	@Test
	public void testQuestion1Page2() {
		Page<User> page2Of3Users = userRepository.findByLevel(2, PageRequest.of(1, 3, Sort.by("registrationDate")));	    
		List<User> users = page2Of3Users.getContent();
		System.out.println(users);		
		
		assertEquals( 4, page2Of3Users.getTotalElements() );
		assertEquals( 3, page2Of3Users.getSize() );
		assertEquals( 2, page2Of3Users.getTotalPages() );
		assertEquals( false, page2Of3Users.hasNext() );
		assertEquals( true, page2Of3Users.hasPrevious() );
		assertEquals( 1, page2Of3Users.getNumber() );
		assertEquals( 1, users.size() );
		assertEquals( 1, page2Of3Users.getNumberOfElements() );
	}
	
	@Test
	public void testQuestion2() {
	    Page<User> page = userRepository.findByLevel( 2, Pageable.unpaged() );	    
    
	    assertEquals( 4, page.getSize() );
	    assertEquals( 1, page.getTotalPages() );
	}
	
	@Test
	public void testQuestion3() {
	    List<User> users = userRepository.findByIsActive( false, Sort.unsorted() );	    
	    
	    assertEquals( 3, users.size() );
	}
	
	@BeforeAll
	public void populateDb() {
		User user1 = new User("john", LocalDate.of(2021, Month.AUGUST, 4), "john@somewhr.com", 1, true);
		User user2 = new User("jane", LocalDate.of(2019, Month.MARCH, 18), "jane@somewhrelse.com", 2, true);
		User user3 = new User("nicole", LocalDate.of(2017, Month.JULY, 21), "nicole@somewhr.com", 1, false);
		User user4 = new User("ravi", LocalDate.of(2018, Month.JUNE, 15), "ravi@somewhrelse.com", 1, false);
		User user5 = new User("alissa", LocalDate.of(2014, Month.APRIL, 5), "alissa@somewhr.com", 2, true);
		User user6 = new User("katie", LocalDate.of(2014, Month.APRIL, 5), "katie@somewhrelse.com", 3, true);
		User user7 = new User("julius", LocalDate.of(2021, Month.FEBRUARY, 9), "julius@somewhrelse.com", 2, true);
		User user8 = new User("jyotsna", LocalDate.of(2020, Month.DECEMBER, 11), "jyotsna@somewhrelse.com", 3, true);
		User user9 = new User("mary", LocalDate.of(2020, Month.MAY, 14), "mary@somewhr.com", 4, false);
		User user10 = new User("judy", LocalDate.of(2015, Month.JANUARY, 8), "judy@somewhrelse.com", 3, true);
		User user11 = new User("dave", LocalDate.of(2014, Month.APRIL, 5), "dave@somewhrelse.com", 2, true);
		
		userRepository.saveAll( List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11 ) );
	}

	@AfterAll
	public void dePopulateDb() {
		userRepository.deleteAll();
	}

}
