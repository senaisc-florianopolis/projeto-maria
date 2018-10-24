import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {

	static CargaService cargaService;

	@BeforeAll
	static void cargaTabelas() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		cargaService = new CargaService();

		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_canal_insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			cargaService.insertCanal(f);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	@Test
	void updateCanal() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();

		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_canal_update-X.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			cargaService.updateCanal(f);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	@Test
	void updateCanalIdInvalido() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();

		assertThrows(SQLException.class, () -> {
			Path f = null;
			try {
				f = Paths.get(classLoader.getResource("dataset/carga_canal_update_id_invalido.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			try {
				cargaService.updateCanal(f);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

	}

	@AfterAll
	static void deletaTabela() {
		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();

		Path f = null;
		try {
			f = Paths.get(classLoader.getResource("dataset/carga_canal_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			cargaService.deleteCanal(f);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
