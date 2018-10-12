import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU4CargaFamiliaTest {

	@Test
	void updateFamilia() {
		ClassLoader classLoader	= getClass().getClassLoader();
		CargaService service = new CargaService();
		Path p;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-familia-update.csv").toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
