import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.SQLException;

import javax.sound.midi.Patch;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
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
		service.updateProduto(update);

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
		service.deleteProduto(delete);

	@BeforeAll
	static void before() {
		service = new CargaService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertProduto(p);

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
		service.updateProduto(update);

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
		service.deleteProduto(delete);
	}

}
	void Teste() {

		service = new CargaService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
			Path p = null;
			try {
				p = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				service.deleteProduto(p);
				System.out.println("O arquivo excluido com sucesso!");
			}
		

	}

//
	@AfterAll
	static void elimina() {
		service = new CargaService();
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();

		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.deleteProduto(p);
		System.out.println("O arquivo excluido com sucesso!");

	}
}
