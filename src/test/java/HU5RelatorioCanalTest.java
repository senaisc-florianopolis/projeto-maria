import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.RelatorioService;

class HU5RelatorioCanalTest {
	static RelatorioService service = null;
	
	@BeforeAll	
	static void beforeAll() {
		ClassLoader classLoader = HU5RelatorioCanalTest.class.getClassLoader();
		Path path = null;
		try {
			path = Paths.get(classLoader.getResource("dataset/relatorio_canal.csv").toURI());
		} catch (URISyntaxException e) {			
			e.printStackTrace();
		}
		service = new RelatorioService(); 
		try {
			service.exportRelatorioCanal(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail("falha");
		}

	}
	
	@Test	
	void RelatorioService() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path path = null;
		try {
			 path = Paths.get(classLoader.getResource("dataset/relatorio_canal.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.exportRelatorioCanal(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail("falha");
		}
	}
}
	

