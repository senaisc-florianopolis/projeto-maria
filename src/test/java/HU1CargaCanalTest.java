import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {
 	
	@Test		
	void testeInsert() throws SQLException {		
		ClassLoader classLoader	= getClass().getClassLoader();
		CargaService service = new CargaService();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_canal.csv").toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		service.cargaCanal(p);
		fail("falhou");
		
	}		
 }	