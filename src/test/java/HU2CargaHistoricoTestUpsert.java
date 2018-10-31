import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU2CargaHistoricoTestUpsert {

	 static CargaService cargaService ;
	
	@BeforeAll
	static void path() {
		cargaService = new CargaService();
		ClassLoader classLoader = HU2CargaHistoricoTestUpsert.class.getClassLoader();
		Path ic = null;
		try {
			ic = Paths.get(classLoader.getResource("dataset/carga_canal_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cargaService.insertCanal(ic);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Path ifa = null;
		try {
			ifa = Paths.get(classLoader.getResource("dataset/carga_familia_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cargaService.insertCanal(ifa);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Path ip = null;
		try {
			ip = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertProduto(ip);
	}
	
	@Test
	void updateHistorico() {
		cargaService = new CargaService();
		ClassLoader classLoader = getClass().getClassLoader();
		Path ih = null;
		try {
			ih = Paths.get(classLoader.getResource("dataset/carga_historico_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertHistorico(ih);
		Path uh = null;
		try {
			uh = Paths.get(classLoader.getResource("dataset/carga_historico_update.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertHistorico(uh);
	}
	
	@Test
	void updateHistoricoIdInvalido() {
		cargaService = new CargaService();
		ClassLoader classLoader = getClass().getClassLoader();
		Path ih = null;
		try {
			ih = Paths.get(classLoader.getResource("dataset/carga_historico_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertHistorico(ih);
		
		assertThrows(SQLException.class, () -> {
			Path uhii = null;
			try {
				uhii = Paths.get(classLoader.getResource("dataset/carga_historico_update_id_invalido.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cargaService.insertHistorico(uhii);
		  });
	}
	
	@Test
	void updateHistoricoColunaEmBranco() {
		cargaService = new CargaService();
		ClassLoader classLoader = getClass().getClassLoader();
		Path ih = null;
		try {
			ih = Paths.get(classLoader.getResource("dataset/carga_historico_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertHistorico(ih);
		
		assertThrows(SQLException.class, () -> {
			Path uhcb = null;
			try {
				uhcb = Paths.get(classLoader.getResource("dataset/carga_historico_update_coluna_banco.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cargaService.insertHistorico(uhcb);
		  });
	}
	
	@Test
	void insertHistorico() {
		cargaService = new CargaService();
		ClassLoader classLoader = getClass().getClassLoader();
		Path ih = null;
		try {
			ih = Paths.get(classLoader.getResource("dataset/carga_historico_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.insertHistorico(ih);
	}
	
	@Test
	void insertHistoricoFormatoErrado() {
		cargaService = new CargaService();
		ClassLoader classLoader = getClass().getClassLoader();
		
		assertThrows(SQLException.class, () -> {
			Path ihfe = null;
			try {
				ihfe = Paths.get(classLoader.getResource("dataset/carga_historico_insert_formato_errado.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cargaService.insertHistorico(ihfe);
		  });
	}
	
	@AfterAll
	static void after() {
		cargaService = new CargaService();
		ClassLoader classLoader = HU2CargaHistoricoTestUpsert.class.getClassLoader();
		Path dc = null;
		try {
			dc = Paths.get(classLoader.getResource("dataset/carga_canal_delete_after_all.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cargaService.deleteCanal(dc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path dp = null;
		try {
			dp = Paths.get(classLoader.getResource("dataset/carga_produto_delete_after_all.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.deleteProduto(dp);
		Path dh = null;
		try {
			dh = Paths.get(classLoader.getResource("dataset/carga_historico_delete_after_all.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargaService.deleteHistorico(dh);
	}
	
}
