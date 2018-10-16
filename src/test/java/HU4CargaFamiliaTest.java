import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU4CargaFamiliaTest {

	static CargaService cargaService ;
	
	@BeforeAll
	static void path() {
		cargaService = new CargaService();
		ClassLoader classLoader = HU4CargaFamiliaTest.class.getClassLoader();
		Path fi = null;
		try {
			fi = Paths.get(classLoader.getResource("dataset/carga_familia_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cargaService.insertCanal(fi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void updateFamilia() {
		cargaService = new CargaService();
		ClassLoader classLoader = getClass().getClassLoader();
		Path uf = null;
		try {
			uf = Paths.get(classLoader.getResource("dataset/carga_historico_update.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertHistorico(uf);
	}
	
	@Test
	void updateFamiliaIdInvalido() {
		cargaService = new CargaService();
		ClassLoader classLoader = getClass().getClassLoader();
		Path ufii = null;
		try {
			ufii = Paths.get(classLoader.getResource("dataset/carga_historico_update_id_invalido.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertHistorico(ufii);
	}
	
	@AfterAll
	static void after() {
		
	}

}
