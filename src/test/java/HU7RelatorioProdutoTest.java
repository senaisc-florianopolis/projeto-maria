import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterAllCallback;

import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.RelatorioService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

public class HU7RelatorioProdutoTest {

	@BeforeAll
	static void before() {
		ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
		CargaService service = new CargaService();
		
		//carga familia
		Path familiaCsv = null;
		try {
			familiaCsv = Paths.get(classLoader.getResource("dataset/carga_familia.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		service.cargaFamilia(familiaCsv);
		
		//carga produto		
		Path produtoCsv = null;
		try {
			produtoCsv = Paths.get(classLoader.getResource("dataset/carga_produto.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.cargaProduto(produtoCsv);
		
		
	}
	@Test
	
	void testeRelatorio() {
		RelatorioService service = new RelatorioService();
		
//		serv
		
//		assertEquals(STATUS.OK, );
		
	}
	
	@AfterAll
	static void after() {
		ProdutoDAO pDao = new ProdutoDAO();
//		pDao.deleteProd();
	}
}