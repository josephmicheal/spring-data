package sdj.auditor;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	// use Spring Security to retrieve the currently logged-in user

    @Override
    public Optional<String> getCurrentAuditor() {
    	return Optional.of( Arrays.asList("john21", "jane22", "joe23").get(new Random().nextInt(3)) );
    }

}