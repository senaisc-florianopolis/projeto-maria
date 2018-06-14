package br.senai.sc.edu.projetomaria.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.senai.sc.edu.projetomaria.model.Familia;
 

public class FamiliaDAO extends AbstractDAO {
	
	public void insert(List<Familia> familia) {
			Statement stmt = null;
			ResultSet rs = null;
			
			for (Familia fl: familia) {
				String sql = "SELECT * FROM familia WHERE ID_FAMILIA = " + "'" + fl.getId() + "'";
				try {
					
					stmt = getConnection().createStatement();
					rs = stmt.executeQuery(sql);
					if (!rs.next()) {
							sql = "INSERT INTO Familia (ID_FAMILIA, CODIGO) VALUES ('" + fl.getId() + "','" + fl.getCodigo()
							+ "') ";
					}
				} catch (SQLException e) {
					// TODO Message for user??
				}
			}
		}
			
		
		public void update(Familia familia) {
			Statement stmt = null;
			ResultSet rs = null;
			String sql = "UPDATE familia SET  " + "'" + familia.getCodigo() + "'" +  
					"WHERE ID_Familia = " +"'" + familia.getId() + "'" ;
			
			try {
					stmt = getConnection().createStatement();
			}	catch (SQLException e) {
					// TODO Message for user??
				
			}
			
	}
	
		
		public void delete(int id) {
			Statement stmt = null;
			ResultSet rs = null;
			
	}
		
}
	