import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class HU3CargaPhaseTest {

	static final Logger LOGGER = LogManager.getLogger();

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

		CargaService service = new CargaService();
		ServiceResponse testin = service.cargaPhase(p);

		assertEquals(testin.getStatus(), ServiceResponse.STATUS.OK);

		int[] response = (int[]) testin.getResponse();

		assertEquals(response[0], 9);
		assertEquals(response[1], 0);

		ServiceResponse test = service.cargaProduto(p);

		assertEquals(test.getStatus(), ServiceResponse.STATUS.OK);

		int[] response2 = (int[]) test.getResponse();
		LOGGER.debug(response2);
		assertEquals(response2[0], 0);
		assertEquals(response2[1], 9);

	}

}