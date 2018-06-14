package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.resource.ResourceManager;

public class CanalDAO extends AbstractDAO{
	
	private Connection conn = null;
	public void  insert(List<Canal> canal){
		
	}
	
	public void update(int id){
		
	}
	
	public void delete(int id){
	try {
		this.conn=getConnection();
		PreparedStatement stm = conn.prepareStatement("DELETE* FROM canal where id canal");
		stm.executeQuery();
		this.conn.close();
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
	
}
