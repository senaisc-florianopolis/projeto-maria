import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

class HU3CargaProdutoTest {
	static CargaService service = null;
	static ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

	@BeforeAll
	static void before() {
		Path update = null;
		Path delete = null;

		try {
			update = Paths.get(classLoader.getResource("dataset/carga_produto_update.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			delete = Paths.get(classLoader.getResource("dataset/carga_produto_delete.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void hu3Bdd2Updatesucesso1() {

		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

		Path update = null;
		try {
			update = Paths.get(classLoader.getResource("dataset/carga_produto_update.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//service.updateProduto(update);

	}

	@Test
	void hu3Bdd6DeleteSucesso1() {

		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

		Path delete = null;
		try {
			delete = Paths.get(classLoader.getResource("dataset/carga_produto_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		//service.deleteProduto(delete);

	}

	@Test
	void test() {
		service = new CargaService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path p = null;
		Path a = null;

		try {
			p = Paths.get(classLoader.getResource("dataset/carga_familia_upsert.csv").toURI());
			a = Paths.get(classLoader.getResource("dataset/carga_produto_upsert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CargaService service = new CargaService();
		ServiceResponse response = service.cargaFamilia(p);
		ServiceResponse response2 = service.cargaProduto(a);

		assertEquals(response.getStatus(), ServiceResponse.STATUS.OK);

		if (a == null) {
			System.out.println(ServiceResponse.STATUS.ERROR);
		}
		if (a != null) {
			System.out.println(ServiceResponse.STATUS.OK);
		}

	}

}