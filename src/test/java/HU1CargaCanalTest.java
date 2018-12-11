import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.List;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

public class HU1CargaCanalTest {
	
	@Test
	void testeInsert() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_canal.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		CargaService service = null;
		service = new CargaService();
		ServiceResponse s = service.cargaCanal(p);
		assertEquals(s.getStatus(), STATUS.OK);
		int[] response = (int[]) s.getResponse();
		assertEquals(response[0], 4);
		assertEquals(response[1], 0);
		
	}	
	@AfterAll
	static void limpandoDB() {
		Database bd = new Database();

		String sqlcanal = "delete from canal";

		try (Connection conn = bd.getDatabaseConnection(); Statement pl = conn.createStatement();) {
			pl.executeUpdate(sqlcanal);
		} catch (SQLException ex) {
			System.err.print(ex.getMessage());
		}

 }	
}
