	import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterAll;
	import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.RelatorioService;

	public class HU7RelatorioProdutoTestErroUm {

		static CargaService cargaService;

		
		@BeforeAll
	    static void cargaTabelas() {
			ClassLoader classLoader = HU7RelatorioProdutoTest.class.getClassLoader();
			cargaService = new CargaService();
			
			Path f = null;
			try {
				f = Paths.get(classLoader.getResource("dataset/carga-familia-insert_will.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			try {
				cargaService.insertFamilia(f);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			

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
			
			Path f = null;
			try {
				f = Paths.get(classLoader.getResource("dataset/carga_familia_delete_will.csv").toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			try {
				cargaService.deleteFamilia(f);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
						
		
		}

		 

	}

