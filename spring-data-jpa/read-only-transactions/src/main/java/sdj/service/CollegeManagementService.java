package sdj.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sdj.entity.Guide;
import sdj.repository.GuideRepository;

@Service
public class CollegeManagementService {
	
	@Autowired
	private GuideRepository guideRepository;

	@Transactional
	public void persistingSomeGuides() {
        Guide guide1 = new Guide("2000MO10789", "Mike Lawson", 1000);
        Guide guide2 = new Guide("2000IM10901", "Ian Lamb", 2000);
        
        guideRepository.save(guide1);
        guideRepository.save(guide2);        
	}
    
    @Transactional
    public void fetchingReadWriteGuide() {
        Guide guide = guideRepository.findById( 1L ).get();        
        guide.setSalary(2500); 
    }    
	
    @Transactional(readOnly = true)
    public void fetchingReadOnlyGuide() {
        Guide guide = guideRepository.findById( 1L ).get();        
        guide.setSalary(2500); 
    }
	
}
