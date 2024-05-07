package sdj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sdj.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//Question 1: Task 1
	List<User> findByOrderByLevelDesc();
	
	//Question 1: Task 2 Answer 1
	List<User> findFirst2ByOrderByLevelDesc();
	
	//Question 1: Task 2 Answer 2
	List<User> findTop2ByOrderByLevelDesc();
	
	//Question 1: Task 3 Answer 1
	User findFirstByOrderByLevelDesc();
	
	//Question 1: Task 3 Answer 2
	User findTopByOrderByLevelDesc();
	
	//Question 1: Task 4 Answer 1
	List<User> findByIsActiveOrLevel(Boolean isActive, Integer level);
	
	//Question 1: Task 4 Answer 2
	List<User> findByIsActiveTrueOrLevel(Integer level);

	//Question 1: Task 5
	List<User> findByEmailContaining(String email);
}
