import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

class HU3CargaPhaseTest {

	static final Logger LOGGER = LogManager.getLogger();

	static CargaService service = null;
	static ClassLoader classLoader = HU3CargaPhaseTest.class.getClassLoader();

	@BeforeAll

	static void beforeAll() {
		Path p = null;
		Path a = null;

		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto.csv").toURI());
			a = Paths.get(classLoader.getResource("dataset/carga_familia.csv").toURI());

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();

		service.cargaFamilia(a);
		service.cargaProduto(p);
	}

	@Test
	void upsert() {

		Path n = null;

		try {
			n = Paths.get(classLoader.getResource("dataset/carga_phase.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		service = new CargaService();
		service.cargaPhase(n);
		ServiceResponse s = service.cargaPhase(n);
		assertEquals(s.getStatus(), STATUS.OK);

	}

	@AfterAll

	static void clear() {
		Database bd = new Database();

		String sqlfamilia = "delete from familia";
		String sqlproduto = "delete from produto";
		String sqlphase = "delete from sku_phase";

		try (Connection conn = bd.getDatabaseConnection(); Statement pl = conn.createStatement();) {
			pl.executeUpdate(sqlfamilia);
			pl.executeUpdate(sqlproduto);
			pl.executeUpdate(sqlphase);

		} catch (SQLException ex) {
			System.err.print(ex.getMessage());
		}

	}

}