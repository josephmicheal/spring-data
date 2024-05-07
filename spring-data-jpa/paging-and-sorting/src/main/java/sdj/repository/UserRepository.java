package sdj.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import sdj.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//First<number>, Top<number>
	List<User> findFirst2ByIsActiveTrue();
	List<User> findTop2ByIsActiveTrue();
	
	//Pageable, Page, Sort
	Page<User> findAll(Pageable pageable);

	//Question 1 Answer 1 - OrderBy
	List<User> findByLevelOrderByRegistrationDateDescUsernameAsc( Integer level );
	
	//Question 1 Answer 2 - Sort
	List<User> findByLevel( Integer level, Sort sort );
	
}
