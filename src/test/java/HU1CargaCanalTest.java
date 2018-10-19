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
		Path p2 = null;
		Path p3 = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			p2 = Paths.get(classLoader.getResource("dataset/carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			p3 = Paths.get(classLoader.getResource("dataset/carga-canal-insert-2.csv").toURI());
		} catch (URISyntaxException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			
		}
		
		try {
			service.insertCanal(p);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		try {
			service.insertCanal(p2);
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
				fail("O é duplicado");
		}
		
		try {
			service.insertCanal(p3);
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
				fail("O arquivo tem um conteúdo inapropriado para esta carga");
		}
		
	}	

}
