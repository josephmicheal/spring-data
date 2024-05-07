package sdj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sdj.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	//@MappedSuperclass Book (base-class) can't be queried; polymorphic queries are not permitted
	/*
	@Query("select b from Book b") 
	List<Book> findAll();	
	*/
	
}
