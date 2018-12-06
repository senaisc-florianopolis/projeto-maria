import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;


class HU3CargaPhaseTest {
    
    //phase1
	static CargaService service = null;
	static ClassLoader classLoader = HU3CargaPhaseTest.class.getClassLoader();

	@BeforeAll
	static void before() {
		Path update = null;
		
		try {
			update = Paths.get(classLoader.getResource("dataset/carga_phase.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

	@Test
	void HU3CargaPhaseTestSucesso() {

		ClassLoader classLoader = HU3CargaPhaseTest.class.getClassLoader();

		Path update = null;
		try {
			update = Paths.get(classLoader.getResource("dataset/carga_phase.csv").toURI());
		} catch (URISyntaxException e) {
			fail("Not yet implemented");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}	