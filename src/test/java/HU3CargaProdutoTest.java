import static org.junit.jupiter.api.Assertions.assertThrows;

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
		System.out.println("----------------------TESTE INSERT SUCESSO ----------------");
		assertThrows(SQLException.class, () -> {
			Path h3suc = null;
			try {
				h3suc = Paths.get(classLoader.getResource("dataset/carga-Produto-insertTESTsucesso.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.insertProduto(h3suc);
			System.out.println("----------------------TESTE INSERT SUCESSO ----------------");
		});

	}

	@Test
	void hu3bdd4inserterro1() {
		System.out.println("----------------------TESTE INSERT ERRO1 ----------------");
		assertThrows(SQLException.class, () -> {
			Path h3er1 = null;
			try {
				h3er1 = Paths.get(classLoader.getResource("dataset/carga-Produto-insertTESTerro1.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.insertProduto(h3er1);
			System.out.println("----------------------TESTE INSERT ERRO1 ----------------");
		});
	}

	@Test
	void hu3bdd4inserterro2() {
		System.out.println("----------------------TESTE INSERT ERRO2 ----------------");
		assertThrows(SQLException.class, () -> {
			Path h3er2 = null;
			try {
				h3er2 = Paths.get(classLoader.getResource("dataset/carga-Produto-insertTESTerro2.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.insertProduto(h3er2);
			System.out.println("----------------------TESTE INSERT ERRO2 ----------------");
		});
	}

	@Test
	void Teste() {

		service = new CargaService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-produto-insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			service.deleteProduto(p);
			System.out.println("O arquivo excluido com sucesso!");
		}

	}
}
