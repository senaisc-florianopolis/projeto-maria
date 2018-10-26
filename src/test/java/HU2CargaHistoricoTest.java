import static org.junit.jupiter.api.Assertions.*;

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
		ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
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

}
