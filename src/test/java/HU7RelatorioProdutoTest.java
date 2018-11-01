import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.RelatorioService;

class HU7RelatorioProdutoTest {

	static CargaService cargaService;

	@BeforeAll
	static void cargaTabelas() {
		ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
		cargaService = new CargaService();

		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga-familia-insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			cargaService.insertFamilia(f);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga-produto-insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		cargaService.insertProduto(p);
	}

	@Test
	void relatorioProdutoSucesso() {
		RelatorioService relatorioService = new RelatorioService();
		try {
			Path tempFile = Files.createTempFile("relatorio_familia_comercial", ".txt");
			relatorioService.exportRelatorioProduto(tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Path tempFile = Files.createTempFile("relatorio_produto", ".txt");
			relatorioService.exportRelatorioProduto(tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterAll
	static void deletaTabela() {
		ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
		cargaService = new CargaService();

		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_familia_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			cargaService.deleteFamilia(f);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	

		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		cargaService.deleteProduto(p);
	}

	@Test
	void sucesso() {
		ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
		RelatorioService relatorioService = new RelatorioService();
		assertThrows(SQLException.class,() -> {
			Path p = null;
			try {
				p = Paths.get(classLoader.getResource("dataset/relatorio_produto.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			relatorioService.exportRelatorioProduto(p);
			System.out.println("N�o h� registro cadastrado!");
			
		});
	}

}
