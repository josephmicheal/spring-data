package sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdj.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
