	import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.RelatorioService;

	class HU7RelatorioProdutoTestErroDois {

		static CargaService cargaService;
		

		
		@BeforeAll
		static void cargaTabelas() {
			ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
			cargaService = new CargaService();


			Path p = null;
			try {
				p = Paths.get(classLoader.getResource("dataset/carga-produto-insert_will.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			cargaService.insertProduto(p);
		}

		@Test
		void relatorioProdutoSucesso() {
			RelatorioService relatorioService = new RelatorioService();
			try {
				Path tempFile = Files.createTempFile("relatorio_familia_comercial_will", ".txt");
				relatorioService.exportRelatorioProduto(tempFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Path tempFile = Files.createTempFile("relatorio_produto_will", ".txt");
				relatorioService.exportRelatorioProduto(tempFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@AfterAll
		static void deletaTabela() {
			ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
			cargaService = new CargaService();

			Path p = null;
			try {
				p = Paths.get(classLoader.getResource("dataset/carga_produto_delete_will.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			cargaService.deleteProduto(p);
			
		}

		 
}
