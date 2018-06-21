package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;


public class CanalDAO extends AbstractDAO{  
	
	private Logger LOGGER = Logger.getLogger(CanalDAO.class.getName());
		
	public ArrayList<Canal> getCanais() {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM maria.canal;";
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			LOGGER.severe(e.getSQLState() + " - " + e.getMessage());
		}
		ArrayList<Canal> canais = new ArrayList<>();
		 try{
		 while(rs.next()){
			 Canal canal = new Canal();
			 canal.setId(rs.getInt("ID_CANAL"));
			 canal.setDescricao(rs.getString("DESCRICAO"));
			 
			 canais.add(canal);
		 }
		 }catch (SQLException e){
		 LOGGER.severe(e.getSQLState() + " - " + e.getMessage());
		 }
		return canais;
		
	}
	public void insert(List<Canal> canal) {
		Statement stmt = null;
		ResultSet rs = null;

		for (Canal cn : canal) {
			String sql = "SELECT * FROM canal WHERE ID_CANAL = " + "'" + cn.getId() + "'";
			try {
				stmt = getConnection().createStatement();
				rs = stmt.executeQuery(sql);
				if (!rs.next()) {
					sql = "INSERT INTO canal ( ID_CANAL, DESCRICAO) VALUES ('" + cn.getId() + "','" + cn.getDescricao()
							+ "') ";
				}
			} catch (SQLException e) {
				// TODO Message for user??
			}
		}
	}

	public void update(Canal canal) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "UPDATE canal SET  " + "'" + canal.getDescricao() + "'" +  
		"WHERE ID_CANAL = " +"'" + canal.getId() + "'" ;
		
		try {
			stmt = getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Message for user
		}

	}

	public void delete(List<Canal> canais){
		Connection conn = getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM canal where id = ?");
			for (Canal canal : canais) {
				ps.setInt(0, canal.getId());
				ps.executeQuery();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
}

