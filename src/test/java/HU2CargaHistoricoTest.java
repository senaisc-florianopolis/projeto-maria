import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.service.CargaService;

class HU2CargaHistoricoTest {
	static CargaService service = null;

	@BeforeAll

	static void before() {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
		Path p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_familia_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		service = new CargaService();
		try {
			service.insertFamilia(p);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
			e1.printStackTrace();
		}

		classLoader = HU2CargaHistoricoTest.class.getClassLoader();
		p = null;
		try {
			p = Paths.get(classLoader.getResource("dataset/carga_produto_insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		service = new CargaService();
		service.insertProduto(p);

		Path b = null;
		try {
			b = Paths.get(classLoader.getResource("dataset/carga-canal-insert.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			service.insertCanal(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testErro1() {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();

		assertThrows(SQLException.class, () -> {
			Path h = null;
			try {
				h = Paths.get(classLoader.getResource("dataset/biruleibe.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h);
		});

	}

	@Test
	void testErro2() {

		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
		assertThrows(SQLException.class, () -> {
			Path h = null;
			try {
				h = Paths.get(classLoader.getResource("dataset/carga_historico_update_vazio.csv").toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.updateHistorico(h);
		});

	}

	@Test
	void testSucesso() {
		ClassLoader classLoader = HU2CargaHistoricoTest.class.getClassLoader();
		Path h = null;
		try {
			h = Paths.get(classLoader.getResource("dataset/carga_historico_update.csv").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.updateHistorico(h);
	}

	@AfterAll
	void limpar() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maria", "root", "root");

		try {
			((Statement) con).executeUpdate("Delete * FROM  canal");
			((Statement) con).executeUpdate("Delete * FROM  familia_comercial");
			((Statement) con).executeUpdate("Delete * FROM  historico");
			((Statement) con).executeUpdate("Delete * FROM  produto");
			((Statement) con).executeUpdate("Delete * FROM  sku_phase");
		} finally {

			con.close();
			con = null;
		}

	}
}
