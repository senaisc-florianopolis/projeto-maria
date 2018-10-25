import static org.junit.jupiter.api.Assertions.fail;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.service.CargaService;

class HU1CargaCanalTest {

	void teste01() throws SQLException {

		ClassLoader classLoader = getClass().getClassLoader();
		Path p = null;

		try {
			CargaService servicoCarga = new CargaService();
			p = Paths.get(classLoader.getResource("dataset/teste.csv").toURI());

			servicoCarga.insertFamilia(p);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

	}
}
