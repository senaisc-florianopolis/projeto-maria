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

class teste01 {

	static CargaService cargaService;

	@Test
	void Teste() {
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
}