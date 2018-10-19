import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU2CargaHistoricoTest {
	static CargaService service = null;
	static ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();

	@BeforeAll
	static void before() {
		Path cf = null;
		Path cp = null;
		Path cc = null;
		Path ch = null;

		try {
			cf = Paths.get(classLoader.getResource("dataset/carga-familia-insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cp = Paths.get(classLoader.getResource("dataset/carga-produto-insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cc = Paths.get(classLoader.getResource("dataset/carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ch = Paths.get(classLoader.getResource("dataset/carga-historico-insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ------------------------------------Services------------------------------ //

		service = new CargaService();
		try {
			service.insertFamilia(cf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertProduto(cp);
		try {
			service.insertCanal(cc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertHistorico(ch);
	}

	@Test
	void hu2bdd6updatesucesso() {
		Path h3suc = null;
		try {
			h3suc = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTsucesso.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertHistorico(h3suc);
		// service.deleteHistorico();
		// usar os asserts pra ver se está tudo correto;
		
		service.insertHistorico(h2suc);
		assertThrows(SQLException.class, () -> {
			service.insertProduto(h2suc);	
		});

	}

	@Test
	void hu2bdd6updateerro1() {
		Path h3er1 = null;
		try {
			h3er1 = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro1.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertHistorico(h3er1);
		// service.deleteHistorico();
		// usar os asserts pra ver se está tudo correto;

	}

	@Test
	void hu2bdd6updateerro2() {
		Path h3er2 = null;
		try {
			h3er2 = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro2.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertHistorico(h3er2);
		// service.deleteHistorico();
		// usar os asserts pra ver se está tudo correto;

	}

}
