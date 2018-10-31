import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.RelatorioService;

class HU7RelatorioProdutoTest {

	static RelatorioService relatorioService;

	@Test
	void sucesso() {
		relatorioService = new RelatorioService();
		ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
		
		assertThrows(SQLException.class,() -> {
			Path p = null;
			try {
				p = Paths.get(classLoader.getResource("dataset/relatorio_produto.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			relatorioService.exportRelatorioProduto(p);
			System.out.println("Não há registro cadastrado!");
			
		});

	}

}
