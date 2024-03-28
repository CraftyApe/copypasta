package de.craftyape.copypasta;

import de.craftyape.copypasta.entities.Pasta;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CopypastaApplicationTests {

	Pasta pasta = new Pasta(0,0);
	Pasta cooked = pasta;
	@Test
	void contextLoads() {
		// dummy lol
		assert pasta.equals(cooked);
	}

}
