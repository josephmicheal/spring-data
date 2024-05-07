package sdj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sdj.entity.User;
import sdj.repository.UserRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainApplicationTests {
	
	@Autowired
	private UserRepository userRepository;

    //Question 1: Task 1
    @Test
    public void testQuestion1Task1() {  
        List<User> users = userRepository.findByOrderByLevelDesc();
        assertEquals( 6, users.size() );        
        System.out.println( users );
    }
    
    //Question 1: Task 2 Answer 1
    @Test
    public void testQuestion1Task2Answer1() {  
        List<User> users = userRepository.findFirst2ByOrderByLevelDesc();
        assertEquals( 2, users.size() );        
        System.out.println( users );
    }
    
    //Question 1: Task 2 Answer 2
    @Test
    public void testQuestion1Task2Answer2() {  
        List<User> users = userRepository.findTop2ByOrderByLevelDesc();
        assertEquals( 2, users.size() );        
        System.out.println( users );
    }
    
    //Question 1: Task 3 Answer 1
    @Test
    public void testQuestion1Task3Answer1() {  
        User user = userRepository.findFirstByOrderByLevelDesc();
        assertEquals( 3, user.getLevel() );        
        System.out.println( user );
    }
    
    //Question 1: Task 3 Answer 2
    @Test
    public void testQuestion1Task3Answer2() {  
        User user = userRepository.findTopByOrderByLevelDesc();
        assertEquals( 3, user.getLevel() );        
        System.out.println( user );
    }
    
    //Question 1: Task 4 Answer 1
    @Test
    public void testQuestion1Task4Answer1() {  
    	List<User> users = userRepository.findByIsActiveOrLevel(true, 1);
        assertEquals( 6, users.size() );        
        System.out.println( users );
    }
    
    //Question 1: Task 4 Answer 2
    @Test
    public void testQuestion1Task4Answer2() {  
    	List<User> users = userRepository.findByIsActiveTrueOrLevel(1);
        assertEquals( 6, users.size() );        
        System.out.println( users );
    }
    
    //Question 1: Task 5
    @Test
    public void testQuestion1Task5() {  
    	List<User> users = userRepository.findByEmailContaining( "else" );
        assertEquals( 3, users.size() );        
        System.out.println( users );
    }
    
	@BeforeAll
	public void populateDb() {
		User user1 = new User("john", LocalDate.of(2021, Month.AUGUST, 4), "john@somewhr.com", 1, true);
		User user2 = new User("jane", LocalDate.of(2019, Month.MARCH, 18), "jane@somewhrelse.com", 2, true);
		User user3 = new User("nicole", LocalDate.of(2017, Month.JULY, 21), "nicole@somewhr.com", 1, false);
		User user4 = new User("ravi", LocalDate.of(2018, Month.JUNE, 15), "ravi@somewhrelse.com", 1, false);
		User user5 = new User("alissa", LocalDate.of(2014, Month.APRIL, 5), "alissa@somewhr.com", 2, true);
		User user6 = new User("katie", LocalDate.of(2014, Month.APRIL, 5), "katie@somewhrelse.com", 3, true);
		
		userRepository.saveAll( List.of(user1, user2, user3, user4, user5, user6) );
	}

	@AfterAll
	public void dePopulateDb() {
		userRepository.deleteAll();
	}

}
