import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class HU5RelatorioCanalTest {

	@Test
	void nomeArquivo() {
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			Path p = Paths.get(classLoader.getResource("dataset/relatorio_canal.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
