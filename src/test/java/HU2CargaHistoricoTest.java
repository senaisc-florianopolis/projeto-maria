import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.service.CargaService;

class HU2CargaHistoricoTest {
	static CargaService service = null;
	
	static ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
	
	@BeforeAll
	static void before() {
		Path c = null;
		try {
			c = Paths.get(classLoader.getResource("dataset/carga_canal_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_familia_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.insertFamilia(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insertProduto(p);
		try {
			service.insertCanal(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testBDD3Sucesso(){
		service = new CargaService();
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		
		Path delete = null;
		try {
			delete = Paths.get(classLoader.getResource("dataset/carga_historico_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.deleteHistorico(delete);
		
	}
	
	@Test
	void testBDD3Erro1(){
		service = new CargaService();
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_insert_idrepetido.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		
		Path delete = null;
		try {
			delete = Paths.get(classLoader.getResource("dataset/carga_historico_delete_idrepetido.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.deleteHistorico(delete);
		
		fail("ERRO!!");
	}
	
	@Test
	void testBDD3Erro2(Path path){
		service = new CargaService();
		Path insert = null;
		try {
			insert = Paths.get(classLoader.getResource("dataset/carga_historico_insert_nada.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.insertHistorico(insert);
		
		Path delete = null;
		try {
			delete = Paths.get(classLoader.getResource("dataset/carga_historico_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service.deleteHistorico(delete);
		
		fail("ERRO!");
	}
	
	@AfterAll
	static void after() {
	}
}
