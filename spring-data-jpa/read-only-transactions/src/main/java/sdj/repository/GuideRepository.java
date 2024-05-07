package sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdj.entity.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long> {
	
}
