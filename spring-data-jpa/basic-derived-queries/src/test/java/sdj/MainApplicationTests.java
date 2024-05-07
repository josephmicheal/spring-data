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

	//Find...By
	@Test
	public void testFindByUsername() {
		User user = userRepository.findByUsername("john");
		assertEquals("john", user.getUsername());
		System.out.println( user );
	}
	
	//Is
	@Test
	public void testFindByUsernameIs() {
		User user = userRepository.findByUsernameIs("john");
		assertEquals("john", user.getUsername());
		System.out.println( user );
	}
	
	//Equals
	@Test
	public void testFindByUsernameEquals() {
		User user = userRepository.findByUsernameEquals("john");
		assertEquals("john", user.getUsername());
		System.out.println( user );
	}
	
	//OrderBy
	@Test
	public void findAllByOrderByRegistrationDate() {
		List<User> users = userRepository.findAllByOrderByRegistrationDate();
		assertEquals( 6, users.size() );
		System.out.println( users );
	}
	
	//OrderBy, Asc
	@Test
	public void findAllByOrderByRegistrationDateAsc() {
		List<User> users = userRepository.findAllByOrderByRegistrationDateAsc();
		assertEquals( 6, users.size() );
		System.out.println( users );
	}
	
	//OrderBy, Desc
	@Test
	public void findAllByOrderByRegistrationDateDesc() {
		List<User> users = userRepository.findAllByOrderByRegistrationDateDesc();
		assertEquals( 6, users.size() );
		System.out.println( users );
	}
	
	//Question 1
	@Test
	public void testQuestion1() {
		List<User> users = userRepository.findByRegistrationDateOrderByUsername( LocalDate.of(2014, Month.APRIL, 5) );
		assertEquals( 2, users.size() );
		System.out.println( users );
	}
	
	//Between
    @Test
    public void testFindByRegistrationDateBetween() {
        List<User> users = userRepository.findByRegistrationDateBetween( LocalDate.of(2017, Month.APRIL, 1), LocalDate.of(2019, Month.MARCH, 31));
        assertEquals( 3, users.size() );
        System.out.println( users );
    }
    
	//Before
    @Test
    public void testFindByRegistrationDateBefore() {
        List<User> users = userRepository.findByRegistrationDateBefore( LocalDate.of(2017, Month.APRIL, 1));
        assertEquals( 2, users.size() );
        System.out.println( users );
    }
    
	//After
    @Test
    public void testFindByRegistrationDateAfter() {
        List<User> users = userRepository.findByRegistrationDateAfter( LocalDate.of(2017, Month.APRIL, 1));
        assertEquals( 4, users.size() );
        System.out.println( users );
    }
    
    //In
    @Test
    public void testFindByLevelIn() {
        List<User> users = userRepository.findByLevelIn( List.of(2, 3) );
        assertEquals( 3, users.size() );
        System.out.println( users );
    }
    
    //NotIn
    @Test
    public void testFindByLevelNotIn() {
        List<User> users = userRepository.findByLevelNotIn( List.of(2, 3) );
        assertEquals( 3, users.size() );
        System.out.println( users );
    }
    
    //LessThan
    @Test
    public void testFindByLevelLessThan() {
        List<User> users = userRepository.findByLevelLessThan( 2 );
        assertEquals( 3, users.size() );        
        System.out.println( users );
    }
    
    //LessThanEqual
    @Test
    public void testFindByLevelLessThanEqual() {  
        List<User> users = userRepository.findByLevelLessThanEqual( 2 );
        assertEquals( 5, users.size() );        
        System.out.println( users );
    }
    
    //GreaterThan
    @Test
    public void testFindByLevelGreaterThan() {
        List<User> users = userRepository.findByLevelGreaterThan( 2 );
        assertEquals( 1, users.size() );        
        System.out.println( users );
    }
    
    //GreaterThanEqual
    @Test
    public void testFindByLevelGreaterThanEqual() {  
        List<User> users = userRepository.findByLevelGreaterThanEqual( 2 );
        assertEquals( 3, users.size() );        
        System.out.println( users );
    }
    
    //StartingWith
    @Test
    public void testFindByUsernameStartingWith() {  
        List<User> users = userRepository.findByUsernameStartingWith( "a" );
        assertEquals( 1, users.size() );        
        System.out.println( users );
    }
    
    //EndingWith
    @Test
    public void testFindByUsernameEndingWith() {  
        List<User> users = userRepository.findByUsernameEndingWith( "a" );
        assertEquals( 1, users.size() );        
        System.out.println( users );
    }
    
    //Containing
    @Test
    public void testFindByUsernameContaining() {  
        List<User> users = userRepository.findByUsernameContaining( "a" );
        assertEquals( 4, users.size() );        
        System.out.println( users );
    }
    
    //IgnoreCase
    @Test
    public void testFindByUsernameContainingIgnoreCase() {  
        List<User> users = userRepository.findByUsernameContainingIgnoreCase( "a" );
        assertEquals( 4, users.size() );        
        System.out.println( users );
    }
    
    //IsNull
    @Test
    public void testFindByUsernameIsNull() {  
        List<User> users = userRepository.findByUsernameIsNull();
        assertEquals( 0, users.size() );        
        System.out.println( users );
    }
    
    //Null
    @Test
    public void testFindByUsernameNull() {  
        List<User> users = userRepository.findByUsernameNull();
        assertEquals( 0, users.size() );        
        System.out.println( users );
    }
    
    //IsNotNull
    @Test
    public void testFindByUsernameIsNotNull() {  
        List<User> users = userRepository.findByUsernameIsNotNull();
        assertEquals( 6, users.size() );        
        System.out.println( users );
    }
    
    //NotNull
    @Test
    public void testFindByUsernameNotNull() {  
        List<User> users = userRepository.findByUsernameNotNull();
        assertEquals( 6, users.size() );        
        System.out.println( users );
    }
    
    //IsNot
    @Test
    public void testFindByLevelIsNot() {  
        List<User> users = userRepository.findByLevelIsNot( 1 );
        assertEquals( 3, users.size() );        
        System.out.println( users );
    }
    
    //Not
    @Test
    public void testFindByLevelNot() {  
        List<User> users = userRepository.findByLevelNot( 1 );
        assertEquals( 3, users.size() );        
        System.out.println( users );
    }
    
    //True
    @Test
    public void testFindByIsActiveTrue() {  
        List<User> users = userRepository.findByIsActiveTrue();
        assertEquals( 4, users.size() );        
        System.out.println( users );
    }

    //False
    @Test
    public void testFindByIsActiveFalse() {  
        List<User> users = userRepository.findByIsActiveFalse();
        assertEquals( 2, users.size() );        
        System.out.println( users );
    }
    
    //Question 2 Answer 1
    @Test
    public void testQuestion2Answer1() {  
        List<User> users = userRepository.findFirst2ByIsActiveAndUsernameEndingWith( true, "e" );
        assertEquals( 2, users.size() );        
        System.out.println( users );
    }
    
    //Question 2 Answer 2
    @Test
    public void testQuestion2Answer2() {  
        List<User> users = userRepository.findFirst2ByIsActiveTrueAndUsernameEndingWith( "e" );
        assertEquals( 2, users.size() );        
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
