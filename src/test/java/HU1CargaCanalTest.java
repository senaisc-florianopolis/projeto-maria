import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import br.senai.sc.edu.projetomaria.service.CargaService;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {
	
	static CargaService service = null;
	
	// HU1 - INSERT (BDD 1) - Antônio / Robson Correia -> Auto Increment no DB
	@BeforeAll
	static void beforeAll() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.insertCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Carga com falha!");
		}
	}

	@AfterAll
	static void afterEach() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.deleteCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Base não foi limpa!");
		}
	}

	// HU1 - UPDATE (BDD 2) - André Pessetti / Larissa Leier
	@Test
	void updateCanal() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-update-hu1-bdd2.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.updateCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Update com falha!");
		}
	}

	// HU1 - DELETE (BDD 3) - André Pessetti
	@Test
	void deleteCanal() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-delete-hu1-bdd3.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.deleteCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Delete com falha!");
		}
	}

	// HU1 - INSERT DUPLICADO (BDD 4) - Thiago Garcia
	@Test
	void InserirNaoDuplicado() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		try {
			service.insertCanal(p);

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Não foi possível inserir dados, pois já existem registros.");
		}
	}

	// HU1 - BDD 6 – Erro de Update – Id não existe (Teste de Sucesso) - Thiago Garcia
	@Test
	void ErroUpdate() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-update-hu1bdd6.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.updateCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Impossivel fazer update, ID não existente!");
		}
	}

	// HU1 - BDD 6 – Erro de Delete – Id não existe (Teste de Falha 1) - Thiago Garcia

	@Test
	void insertCanal() {

		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		Path i = null;
		try {
			i = Paths.get(classLoader.getResource("dataset/carga-canal-insert-hu1bdd6.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.insertCanal(i);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Path j = null;
		try {
			j = Paths.get(classLoader.getResource("dataset/carga-canal-delete-hu1bdd6.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.deleteCanal(j);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}