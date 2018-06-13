package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;


public class CanalDao {
	
	private Logger LOGGER = Logger.getLogger(CanalDao.class.getName());
	private Connection connection;

	public CanalDao() {
		super();
		try {
			// ?user=admin&password=secreto
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/maria?user=root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.severe(e.getSQLState() + " - " + e.getMessage());
		}
	}
	
	public ArrayList<Canal> getCanais() {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM maria.canal;";
		try {
			stmt = this.connection.createStatement();
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

