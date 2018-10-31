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

class HU2CargaHistoricoTest {

	static CargaService cargaService;

	@BeforeAll
	static void cargaTabelas() throws SQLException {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
		cargaService = new CargaService();

		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_historico_insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		cargaService.insertHistorico(f);

		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_historico_insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		cargaService.insertHistorico(p);
	}

	@Test
	void relatorioHistoricoSucesso() {
		RelatorioService relatorioService = new RelatorioService();
		try {
			Path tempFile = Files.createTempFile("relatorio_historico_comercial", ".txt");
			relatorioService.exportRelatorioHistorico(tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Path tempFile = Files.createTempFile("relatorio_historico", ".txt");
			relatorioService.exportRelatorioHistorico(tempFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterAll
	static void deletaTabela() {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();

		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_historico_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		cargaService.deleteHistorico(f);

		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_historico_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		cargaService.deleteHistorico(p);
	}
	
	
	static CargaService service = null;
	
	static ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
	
	@BeforeAll
	static void before() {
		
		service = new CargaService();
		
		Path c = null;
		Path f = null;
		Path p = null;
		
		
		try {
			c = Paths.get(classLoader.getResource("dataset/carga_canal_insert_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.insertCanal(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_familia_insert_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.insertFamilia(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_insert_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertProduto(p);
	}
	
	@Test
	void testBDD3Sucesso(){
		
		System.out.println("------------------TESTE BDD3 SUCESSO------------------");
		
		service = new CargaService();
		
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_insert_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		
		
		Path delete = null;
		try {
			delete = Paths.get(classLoader.getResource("dataset/carga_historico_delete_vitorhu2.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.deleteHistorico(delete); 
		
		System.out.println("------------------TESTE BDD3 SUCESSO------------------");
	}
	
	@Test
	void testBDD3Erro1(){
		
		System.out.println("------------------TESTE BDD 3 ERRO1------------------");
		
		service = new CargaService();
		
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_insert_idrepetido.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		
		assertThrows(SQLException.class, () -> {
			Path delete = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga_historico_delete_idrepetido.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete);
			System.out.println("------------------TESTE BDD3 ERRO1------------------");
		});
	}
	
	@Test
	void testBDD3Erro2(){
		
		System.out.println("------------------TESTE BDD3 ERRO2------------------");
		
		service = new CargaService();
		
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_insert_nada.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		
		assertThrows(SQLException.class, () -> {
			Path delete = null;
			try {
				delete = Paths.get(classLoader.getResource("dataset/carga_historico_delete_vitorhu2.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			service.deleteHistorico(delete);
			System.out.println("------------------TESTE BDD3 ERRO2------------------");
		});

	}

	@Test
	void testBDD9Sucesso() {
		
		System.out.println("------------------TESTE BDD9 SUCESSO------------------");
		
		service = new CargaService();
		
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_insert_branco.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		System.out.println("------------------TESTE BDD9 SUCESSO------------------");
	
	}
	}
	
