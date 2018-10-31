import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU4CargaFamiliaTest2 {

	@Test
	
		void updateFamilia() {
			ClassLoader classLoader	= getClass().getClassLoader();
			CargaService service = new CargaService();
			Path p2 = null;
			try {
				p2 = Paths.get(classLoader.getResource("dataset/carga-familia-update.csv").toURI());
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				service.updateFamilia(p2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("Dados não atualizados, não existe informações para atualizar");
			}
		}
	
	
	
	

}
