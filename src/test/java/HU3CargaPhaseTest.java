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
		Path b = null;
		Path t = null;


		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto.csv").toURI());
			a = Paths.get(classLoader.getResource("dataset/carga_historico.csv").toURI());
			t = Paths.get(classLoader.getResource("dataset/carga_phase.csv").toURI());


		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();

		service.cargaHistorico(a);
		service.cargaProduto(p);
		service.cargaPhase(p);


	}

	// phase1

	@Test
	void upsert() {

		Path p = null;

		try {
			p = Paths.get(classLoader.getResource("dataset/carga_phase.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		service = new CargaService();
		service.cargaPhase(p);
		ServiceResponse s = service.cargaPhase(p);
		assertEquals(s.getStatus(), STATUS.OK);

	}

	@AfterAll

	static void clear() {
		Database bd = new Database();

		String sqlhistorico = "delete from familia";
		String sqlproduto = "delete from produto";

		try (Connection conn = bd.getDatabaseConnection(); Statement pl = conn.createStatement();) {
			pl.executeUpdate(sqlhistorico);
			pl.executeUpdate(sqlproduto);
		} catch (SQLException ex) {
			System.err.print(ex.getMessage());
		}

	}

}