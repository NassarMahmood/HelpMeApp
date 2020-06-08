package demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Disabled
public class ApplicationTests {
	@Test
	public void testGeneralHealth() {
		// make sure spring boot initialize environment properly
	}
}
