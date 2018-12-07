import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

class HU3CargaProdutoTest {

	private static final Logger LOGGER = LogManager.getLogger();

	@BeforeAll
	public static void preparando() {
		CargaService service = new CargaService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path p = null;

		try {
			p = Paths.get(classLoader.getResource("dataset/carga_familia.csv").toURI());

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		LOGGER.debug(service.cargaFamilia(p));
	}

	@Test
	void test() {

		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

		Path a = null;

		try {
			a = Paths.get(classLoader.getResource("dataset/carga_produto.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CargaService service = new CargaService();
		ServiceResponse testin = service.cargaProduto(a);

		assertEquals(testin.getStatus(), ServiceResponse.STATUS.OK);

		int[] response = (int[]) testin.getResponse();

		assertEquals(response[0], 13);
		assertEquals(response[1], 0);

		ServiceResponse testup = service.cargaProduto(a);

		assertEquals(testup.getStatus(), ServiceResponse.STATUS.OK);

		int[] response2 = (int[]) testup.getResponse();
		LOGGER.debug(response2);
		assertEquals(response2[0], 0);
		assertEquals(response2[1], 13);

	}

	@AfterAll
	static void limpando() {
		Database bd = new Database();

		String sqlfamilia = "delete from familia";
		String sqlproduto = "delete from produto";

		try (Connection conn = bd.getDatabaseConnection(); Statement pl = conn.createStatement();) {
			pl.executeUpdate(sqlfamilia);
			pl.executeUpdate(sqlproduto);
		} catch (SQLException ex) {
			System.err.print(ex.getMessage());
		}

	}
}
