import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.sound.midi.Patch;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU3CargaProdutoTest {
	static CargaService service = null;

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
		CargaService.insertProduto(p);

	}

	@Test
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
