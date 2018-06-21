package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;


public class CanalDao extends AbstractDAO{  
	
	private Logger LOGGER = Logger.getLogger(CanalDao.class.getName());
		
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
	
	
	
}

