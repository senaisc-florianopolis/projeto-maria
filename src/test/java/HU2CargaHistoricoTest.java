import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

class HU2CargaHistoricoTest {
	
	static CargaService service = null;
	
	@BeforeAll
	
	static void beforeAll() {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();		
		Path q = null;
		Path r = null;
		
		try {
			q = Paths.get(classLoader.getResource("dataset/carga_canal_upsert.csv").toURI());
			r = Paths.get(classLoader.getResource("dataset/carga_produto_upsert.csv").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();	
		
		service.cargaCanal(q);
		service.cargaProduto(r);		
	}
	
	
	@Test
	void upsert() {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
		Path p = null;
		
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_historico_upsert.csv").toURI());			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		service = new CargaService();
		//service.cargaHistorico(p);
		ServiceResponse s = service.cargaHistorico(p);
		assertEquals(s.getStatus(), STATUS.OK);
		int[] response = (int[]) s.getResponse();
		assertEquals(response[0], 4);
		assertEquals(response[1], 0);
		
		ServiceResponse x = service.cargaHistorico(p);
		assertEquals(x.getStatus(), STATUS.OK);
		int[] response1 = (int[]) x.getResponse();
		assertEquals(response1[0], 0);
		assertEquals(response1[1], 4);
	}
}	