import java.sql.Connection;

import br.senai.sc.edu.projetomaria.dao.AbstractDAO;

public class Database extends AbstractDAO {
	
	public Connection getDatabaseConnection() {
		return this.getConnection();
	}

}
