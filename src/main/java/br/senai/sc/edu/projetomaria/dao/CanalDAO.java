package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.resource.ResourceManager;

public class CanalDAO extends AbstractDAO {

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
			// TODO Message for user??
		}

	}

	public void delete(int id) {
		Statement stmt = null;
		ResultSet rs = null;

	}

}
