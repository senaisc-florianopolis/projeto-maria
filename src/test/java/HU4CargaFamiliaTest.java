import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

class HU4CargaFamiliaTest {

	@Test
	void testeInsert() {
		ClassLoader classLoader = HU4CargaFamiliaTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_familia.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		CargaService service = null;
		service = new CargaService();
		ServiceResponse s = service.cargaFamilia(p);
		assertEquals(s.getStatus(), STATUS.OK);
		int[] response = (int[]) s.getResponse();
		assertEquals(response[0], 0);
		assertEquals(response[1], 5);

	}
}
