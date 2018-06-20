package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CanalDAO extends AbstractDAO {

	private static final Logger LOGGER = LogManager.getLogger();

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
			LOGGER.info(Messages.ERRO_EXECUCAO_DELETE);
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
