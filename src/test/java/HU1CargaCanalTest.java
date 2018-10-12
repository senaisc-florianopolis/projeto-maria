import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.protobuf.Service;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {
	
	static CargaService service = null;
	
	@BeforeAll
	static void beforeAll() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		Path p = null;
		try {
		   p = Paths.get(classLoader.getResource("dataset/carga_canal_insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		service = new CargaService();
		try {
			service.insertCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Deu ruim!");
		}
	}
	
	@Test
	void deleteCanal() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_canal_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.deleteCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Deu ruim!");
		}
	}
}