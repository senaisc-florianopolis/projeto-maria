import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

class HU3CargaProdutoTest {
	
	@BeforeAll
	public static void preparando() {
		CargaService service = new CargaService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path p = null;

		try {
			p = Paths.get(classLoader.getResource("dataset/carga_familia.csv").toURI());

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		service.cargaFamilia(p);
	}

	@Test
	void test() {

		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

		Path a = null;

		try {
			a = Paths.get(classLoader.getResource("dataset/carga_produto.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CargaService service = new CargaService();
		ServiceResponse testin = service.cargaProduto(a);

		assertEquals(testin.getStatus(), ServiceResponse.STATUS.OK);

		int[] response = (int[]) testin.getResponse();

		assertEquals(response[0], 13);
		assertEquals(response[1], 0);

		ServiceResponse testup = service.cargaProduto(a);

		assertEquals(testup.getStatus(), ServiceResponse.STATUS.OK);

		int[] response2 = (int[]) testup.getResponse();
		assertEquals(response2[0], 0);
		assertEquals(response2[1], 13);
	}

}