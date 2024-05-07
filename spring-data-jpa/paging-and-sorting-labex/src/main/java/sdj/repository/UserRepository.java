package sdj.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import sdj.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Page<User> findAll( Pageable pageable );
	
	Page<User> findByLevel( Integer level, Pageable pageable );
	
	List<User> findByIsActive( Boolean isActive, Sort sort );	
	
}
