import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;

import br.senai.sc.edu.projetomaria.service.RelatorioService;

class HU8RelatorioEstimativaTest {
	static RelatorioService service = null;
	
	@BeforeAll
	static void before() {
		service = new RelatorioService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertProduto(p);
	}
}
