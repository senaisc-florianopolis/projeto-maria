package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.model.Familia;


public class FamiliaDAO extends AbstractDAO {

	private static final String INSERIR_FAMILIA = null;
	private static final String ATUALIZAR_FAMILIA = null;
	private static final String DELETAR_FAMILIA = null;
	private Logger LOGGER = Logger.getLogger(FamiliaDAO.class.getName());

	public ArrayList<Familia>getFamilias() {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM maria.familia;";
		
		ArrayList<Familia> familias = new ArrayList<>();
		try (Connection conn = getConnection()) {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Familia familia = new Familia();
				familia.setId(rs.getInt("ID_FAMILIA"));
				familia.setCodigo(rs.getString("CODIGO"));
				familias.add(familia);
			}
		} catch (SQLException e) {
				LOGGER.severe(e.getSQLState() + " - " + e.getMessage());	
		}
		return familias;
	}
	
	public void insert (List<Familia> familia) throws SQLException {
		Statement stmt = null;
		int rs;
		int id;
		ResultSet id_reference = null;
		String id_reference_sql = "SELECT ID_FAMILIA_COMERCIAL from familia_comercial order by ID_FAMILIA_COMERCIAL desc limit 1;";
		stmt = getConnection().createStatement();
		id_reference = stmt.executeQuery(id_reference_sql);
		id = id_reference.getInt("ID_FAMILIA");
		for (Familia fl: familia) {
			if(fl.getId() <= id){
				String sql = "INSERT INTO familia_comercial (ID_FAMILIA_COMERCIAL, COD_FAMILIA_COMERCIAL) VALUES ('" + fl.getId() + 1 + "','" + fl.getCodigo()+ "'); ";
				
				try {
					stmt = getConnection().createStatement();
					rs = stmt.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println(e);
				}
			}else{
				String sql = "INSERT INTO familia_comercial (ID_FAMILIA_COMERCIAL, COD_FAMILIA_COMERCIAL) VALUES ('" + fl.getId() + "','" + fl.getCodigo()+ "'); ";
				try {
					stmt = getConnection().createStatement();
					rs = stmt.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
			LOGGER.info(INSERIR_FAMILIA);
		}
	}


	public void update(Familia familia) {
		Statement stmt = null;
		int rs;
		String sql = "UPDATE familia_comercial SET COD_FAMILIA_COMERCIAL = " + "'" + familia.getCodigo() + "'" +
				" WHERE ID_FAMILIA_COMERCIAL = " +"'" + familia.getId() + "';" ;
		System.out.println(sql);	
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeUpdate(sql);
			LOGGER.info(ATUALIZAR_FAMILIA);
		}	catch (SQLException e) {
			// TODO Message for user??

		}

	}


	public void delete(List<Familia>familias) {
		Statement stmt = null;
		int rs;
		
		for (Familia fl: familias) {
			String sql = "DELETE FROM familia_comercial WHERE ID_FAMILIA_COMERCIAL = " + fl.getId() + ";" ;
			System.out.println(sql);
			try {
				stmt = getConnection().createStatement();
				rs = stmt.executeUpdate(sql);
				LOGGER.info(DELETAR_FAMILIA);
			} catch (SQLException e) {
				// TODO Message for user??
				System.out.println(e);
			}
		}
	}
}