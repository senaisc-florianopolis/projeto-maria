import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;
import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {
	
	static CargaService service = null;
	
	@BeforeAll
	static void beforeAll() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		Path p = null;
		try {
		   p = Paths.get(classLoader.getResource("dataset/hu1-carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.insertCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Importação deu falha!");
		}
	}
	
	// HU1 - UPDATE (BDD 2)
	@Test
	void updateCanal() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/hu1-bdd2-carga-canal-update.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.updateCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Update deu ruim!");
		}
	}
	
	// HU1 - DELETE (BDD 3)
	@Test
	void deleteCanal() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/hu1-bdd2-carga-canal-delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.deleteCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Deletar deu ruim!");
		}
	}
	
	
}