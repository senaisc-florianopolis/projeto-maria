import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

class HU3CargaPhaseTest {

	static CargaService service = null;
	static ClassLoader classLoader = HU3CargaPhaseTest.class.getClassLoader();

	@BeforeAll

	static void beforeAll() {
		Path p = null;

		try {
			p = Paths.get(classLoader.getResource("dataset/carga_phase.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();

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

}