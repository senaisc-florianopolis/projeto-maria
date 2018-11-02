import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.RelatorioService;

class HU5RelatorioCanalTest {
	static RelatorioService service = new RelatorioService();

	
	void insertCanal() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_canal_insert.csv").toURI());
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		service.insertCanal(p);
	}
	//HU5RelatorioCanalTest-BDD1
    @Test
	void RelatorioService() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path path = null;
		try {
			path = Paths.get(classLoader.getResource("dataset/relatorio_canal.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail("falhou test");
		}
		try {
			service.exportRelatorioCanal(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail("falhou try");
		}
	}
    
    @Test
	void RelatorioServiceErro1() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path path = null;
		try {
			path = Paths.get(classLoader.getResource("dataset/falha1semcsv.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail("falhou test");
		}
		try {
			service.exportRelatorioCanal(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail("falhou try");
		}
	}
    
    @Test
	void RelatorioServiceErro2() {
		ClassLoader classLoader = getClass().getClassLoader();
		Path path = null;
		try {
			path = Paths.get(classLoader.getResource("dataset/falha2carga-canal-update-id-nao-existe.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail("falhou test");
		}
		try {
			service.exportRelatorioCanal(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail("falhou try");
		}
	}
}
