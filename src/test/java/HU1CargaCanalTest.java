import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {
	
	@Test
	void testeInsert() {
		ClassLoader classLoader	= getClass().getClassLoader();
		CargaService service = new CargaService();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		try {
			service.insertCanal(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("falhou");
		
	}

}
