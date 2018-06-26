package br.senai.sc.edu.projetomaria.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.model.Familia;


public class FamiliaDAO extends AbstractDAO {

	private Logger LOGGER = Logger.getLogger(FamiliaDAO.class.getName());

	

	public ArrayList<Familia>getFamilias() {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM maria.familia;";
		
		try {
			
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
				LOGGER.severe(e.getSQLState() + " - " + e.getMessage());
				
		}
		ArrayList<Familia> familias = new ArrayList<>();
		try {
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
	
	public void insert (List<Familia> familia) {
		Statement stmt = null;
		int rs;

		for (Familia fl: familia) {
			String sql = "INSERT INTO familia_comercial (ID_FAMILIA_COMERCIAL, COD_FAMILIA_COMERCIAL) VALUES ('" + fl.getId() + "','" + fl.getCodigo()+ "'); ";
			System.out.println(sql);
			try {
				stmt = getConnection().createStatement();
				rs = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e);
			}
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
			} catch (SQLException e) {
				// TODO Message for user??
				System.out.println(e);
			}
		}
	}
}