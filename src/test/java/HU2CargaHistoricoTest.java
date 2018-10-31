import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU2CargaHistoricoTest {

	static CargaService service = null;

	static ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();

	@BeforeAll
	static void before() {

		service = new CargaService();

		Path c = null;
		Path f = null;
		Path p = null;

		try {
			c = Paths.get(classLoader.getResource("dataset/carga_canal_insert_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.insertCanal(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			f = Paths.get(classLoader.getResource("dataset/carga_familia_insert_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.insertFamilia(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_insert_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertProduto(p);
	}

	@Test
	void hu2bdd6updatesucesso() {
		System.out.println("----------------------TESTE UPDATE SUCESSO ----------------");
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_inserttestes.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		assertThrows(SQLException.class, () -> {
			Path h2suc = null;
			try {
				h2suc = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTsucesso.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h2suc);

			Path delete = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga_historico_inserttestes.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete);
			Path delete2 = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTsucesso.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete2);
			System.out.println("----------------------TESTE UPDATE SUCESSO ----------------");
		});

	}
	@Test
	void hu2bdd6updateerro1() {
		System.out.println("----------------------TESTE UPDATE ERRO1 ----------------");
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_inserttestes.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		assertThrows(SQLException.class, () -> {
			Path h2er1 = null;
			try {
				h2er1 = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro1.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h2er1);
			Path delete = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga_historico_inserttestes.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete);
			Path delete2 = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro1.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete2);
			System.out.println("----------------------TESTE UPDATE ERRO1 ----------------");
		});

	}

	@Test
	void hu2bdd6updateerro2() {
		System.out.println("----------------------TESTE UPDATE ERRO2 ----------------");
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_inserttestes.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		assertThrows(SQLException.class, () -> {
			Path h2er2 = null;
			try {
				h2er2 = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro2.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h2er2);
			Path delete = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga_historico_inserttestes.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete);
			Path delete2 = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga-historico-updateTESTerro2.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete2);
			System.out.println("----------------------TESTE UPDATE ERRO2 ----------------");
		});

	}

}
