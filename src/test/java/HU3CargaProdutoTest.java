import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU3CargaProdutoTest {
	
	
	static CargaService service = null;
	
	// HU3 - BDD3 - DELETE PRODUTO ////
	
	@BeforeAll
	
	static void beforeAll() {
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();		
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_familia_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.insertFamilia(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	@Test
	void deleteProduto() {
		
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path i = null;
		try {
			i = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service = new CargaService();
		service.insertProduto(i);

		
		///////////////////////////
		
		Path j = null;
		try {
			j = Paths.get(classLoader.getResource("dataset/carga_produto_delete.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.deleteProduto(j);
		
		
	}
	
	@AfterAll	
	static void limparTeste() {
		ClassLoader classLoader = HU3CargaProdutoTest.class.getClassLoader();
		Path k = null;
		try {
			k = Paths.get(classLoader.getResource("dataset/carga_familia_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.deleteFamilia(k);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Path l = null;
		try {
			l = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.deleteProduto(l);
	}
		
		
	// HU3.1 - BDD3 - INSERT DE RELACIONAMENTO DE PRODUTOS JÁ CADASTRADOS NA TABELA PHASE -  ////
}