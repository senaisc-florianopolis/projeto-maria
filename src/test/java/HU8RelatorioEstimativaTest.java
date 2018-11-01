import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class HU8RelatorioEstimativaTest {

	@Test
	void estimativa() {
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			Path p = Paths.get(classLoader.getResource("dataset/relatorio_historico.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  }
	}
}



