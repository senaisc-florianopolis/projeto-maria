import static org.junit.Assert.assertTrue;

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
	CargaService service = null;
	
	ClassLoader classLoader = getClass().getClassLoader();
	
	@BeforeAll
	void before() {
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
	void testSucesso(){
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
		Historico h = new Historico();
		
		assertTrue(h == null);
	}
	
	@Test
	void testErro1(Path path){
		service.deleteHistorico(path);
		//usar os asserts pra ver se está tudo correto;
	}
	
	@Test
	void testErro2(Path path){
		service.deleteHistorico(path);
		//usar os asserts pra ver se está tudo correto;
	}
	
	@AfterAll
	void after() {
		Path c = null;
		try {
			c = Paths.get(classLoader.getResource("dataset/carga_canal_delete.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_familia_delete.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path h = null;
		try {
			h = Paths.get(classLoader.getResource("dataset/carga_historico_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.deleteFamilia(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.deleteProduto(p);
		try {
			service.deleteCanal(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.deleteHistorico(h);		}

}
