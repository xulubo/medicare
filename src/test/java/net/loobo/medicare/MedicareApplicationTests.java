package net.loobo.medicare;

import net.loobo.medicare.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicareApplicationTests {

	@Autowired
	private EmailService emailService;

	@Test
	void contextLoads() {
		emailService.sendEmail("xulubo@gmail.com", "test", "test");
	}

}
