import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

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
			fail("Not yet implemented");
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
	void hu3Bdd2updateErro1() {

		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

		Path update = null;
		try {
			update = Paths.get(classLoader.getResource("dataset/carga_produto_updateError.csv").toURI());
		} catch (URISyntaxException e) {
			fail("Not yet implemented");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//service.updateProduto(update);

	}

	@Test
	void hu3Bdd6DeleteErro1() {

		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

		Path delete = null;
		try {
			delete = Paths.get(classLoader.getResource("dataset/carga_produto_deleteError.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		//service.deleteProduto(delete);
	}

}