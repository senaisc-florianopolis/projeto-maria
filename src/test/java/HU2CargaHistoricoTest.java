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
		
	@BeforeAll
	static void before() {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
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
	void caso1sucesso(Path path) {
		service.deleteHistorico(path);
		// usar os asserts pra ver se está tudo correto;

	}
	void caso1erro1(Path path) {
		service.deleteHistorico(path);
		// usar os asserts pra ver se está tudo correto;

	}
	void caso1erro2(Path path) {
		service.deleteHistorico(path);
		// usar os asserts pra ver se está tudo correto;

	}

}
