import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {
	static CargaService service = null;

	// HU1 - BDD 4 - Não Duplicação (Teste de sucesso)
	@Test
	void InserirNaoDuplicado() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/hu1bdd4carga_canal_insert.csv").toURI());
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		try {
			service.insertCanal(p);
			service.insertCanal(p);

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Alteração Realizada com sucesso!");
		}
	}
	

	// HU1 - BDD 6 – Erro de Update – Id não existe (Teste de Sucesso)
	@Test
	void ErroUpdate() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/hu1bdd6carga_canal_update.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.updateCanal(p);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Impossivel fazer update, ID não existente!");
		}
	}

	// HU1 - BDD 6 – Erro de Delete – Id não existe (Teste de Falha 1)

	@Test
	void insertCanal() {

		ClassLoader classLoader = HU1CargaCanalTest.class.getClassLoader();
		Path i = null;
		try {
			i = Paths.get(classLoader.getResource("dataset/hu1bdd6carga_canal_insert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		try {
			service.insertCanal(i);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		///////////////////////////

		Path j = null;
		try {
			j = Paths.get(classLoader.getResource("dataset/hu1bdd6carga_canal_delete.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			service.deleteCanal(j);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}