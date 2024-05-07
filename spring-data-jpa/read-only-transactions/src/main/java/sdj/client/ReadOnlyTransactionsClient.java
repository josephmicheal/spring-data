package sdj.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import sdj.service.CollegeManagementService;

@Component
public class ReadOnlyTransactionsClient implements ApplicationRunner {
	
	@Autowired
	private CollegeManagementService cMService;

	@Override
	public void run(ApplicationArguments args) throws Exception {	
		
		//pre-populating db
		cMService.persistingSomeGuides();
		
		//raising salary to 2500 using READ_WRITE transaction
		//cMService.fetchingReadWriteGuide();
		
		//raising salary to 2500 using READ_ONLY transaction
		cMService.fetchingReadOnlyGuide();
	}

}