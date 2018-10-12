import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;

import br.senai.sc.edu.projetomaria.service.RelatorioService;

class HU5RelatorioCanalTest {
	static RelatorioService service = null;
	
	@BeforeAll
	
	static	void before() {
		ClassLoader classLoader = HU5RelatorioCanalTest.class.getClassLoader();
		Path p = null;
		try {
			 p = Paths.get(classLoader.getResource("dataset/relatorio_canal.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service = new RelatorioService();
		service.insertProduto(p);
		service.insertCanal(p);
	}

}
