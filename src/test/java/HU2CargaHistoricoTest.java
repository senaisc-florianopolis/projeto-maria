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
		System.out.println("----------------------TESTE UPDATE SUCESSO ---------------");
		assertThrows(SQLException.class, () -> {
			Path h2suc = null;
			try {
				h2suc = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTsucesso.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h2suc);
			System.out.println("----------------------TESTE UPDATE SUCESSO ----------------");
		});

	}

	@Test
	void hu2bdd6updateerro1() {
		System.out.println("----------------------TESTE UPDATE ERRO1 ----------------");
		assertThrows(SQLException.class, () -> {
			Path h2er1 = null;
			try {
				h2er1 = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro1.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h2er1);
			System.out.println("----------------------TESTE UPDATE ERRO1 ----------------");
		});

	}

	@Test
	void hu2bdd6updateerro2() {
		System.out.println("----------------------TESTE UPDATE ERRO2 ----------------");
		assertThrows(SQLException.class, () -> {
			Path h2er2 = null;
			try {
				h2er2 = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro2.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h2er2);
			System.out.println("----------------------TESTE UPDATE ERRO2 ----------------");
		});

	}

}
