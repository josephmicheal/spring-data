package sdj;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sdj.entity.Book;
import sdj.repository.BookRepository;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT )
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainApplicationTests {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testAuditing() {
    	Book book = new Book();
        book.setIsbn("001-SDJ");
        book.setTitle("Core");		

        bookRepository.save(book);
        
        book.setTitle("Core, 2nd Edition");  
        
        bookRepository.save(book);
	}

}
