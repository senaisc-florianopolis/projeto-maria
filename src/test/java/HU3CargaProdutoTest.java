import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU3CargaProdutoTest {
	static CargaService service = null;
	static ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
	
	@BeforeAll
	static void before() {
		Path cf = null;
		Path cp = null;
		
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
		
		// ---------------------- Services ------------------------
		
	// ------------------------------------Services------------------------------ //
		
		service = new CargaService();
		try {
			service.insertFamilia(cf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertProduto(cp);
	}

	@Test
	void hu3bdd4insertsucesso() {
		Path h2suc = null;
		try {
			h2suc = Paths.get(classLoader.getResource("dataset/carga-produto-insertTESTsucesso.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertHistorico(h2suc);
		//service.deleteHistorico();
		// usar os asserts pra ver se está tudo correto;

	}
	@Test
	void hu3bdd4inserterro1() {
		Path h2er1 = null;
		try {
			h2er1 = Paths.get(classLoader.getResource("dataset/carga-produto-insertTESTerro1.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertHistorico(h2er1);
		//service.deleteHistorico();
		// usar os asserts pra ver se está tudo correto;

	}
	@Test
	void hu3bdd4inserterro2() {
		Path h2er2 = null;
		try {
			h2er2 = Paths.get(classLoader.getResource("dataset/carga-produto-insertTESTerro2.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertHistorico(h2er2);
		//service.deleteHistorico();
		// usar os asserts pra ver se está tudo correto;

	}

}
