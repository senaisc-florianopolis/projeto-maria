package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.resource.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;

public class CanalDAO extends AbstractDAO {

	private static final String SUCESSO_DELETE_CANAL = null;
	private static final String REGISTRO_INCLUIDO_SUCESSO = null;
	private static final String REGISTRO_ = null;
	private static final String REGISTRO_CADASTRO_SUCESSO = null;
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
		try {
			while (rs.next()) {
				Canal canal = new Canal();
				canal.setId(rs.getInt("ID_CANAL"));
				canal.setDescricao(rs.getString("DESCRICAO"));

				canais.add(canal);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.getSQLState() + " - " + e.getMessage());
		}
		return canais;

	}

	public void insert(List<Canal> canal) {
		PreparedStatement stmt = null;
		String sql =  SQL.INSERT_CANAL;
		try {
			stmt =  getConnection().prepareStatement(sql);
			for (Canal cn : canal) {
					stmt.setInt(1, cn.getId());
					stmt.setString(2, cn.getDescricao());
					stmt.execute();				
					LOGGER.info(REGISTRO_INCLUIDO_SUCESSO);
			}
		} catch (SQLException e) {
			// TODO Message for user??
			LOGGER.info(Messages.REGISTRO_CADASTRO_SUCESSO);
		}
	}

	public void update(Canal canal) {
		Connection conn = getConnection();
		String sql = SQL.UPDATE_CANAL;

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(0, canal.getId());
				ps.setString(1, canal.getDescricao());
				ps.execute();
				LOGGER.info(Messages.REGISTRO_SALVO_SUCESSO);
					
		} catch (SQLException e) {
			// TODO Message for user
			
			LOGGER.info(Messages.REGISTRO_ALTERADO_SUCESSO);
		}

	}

	public void delete(List<Canal> canais) {
		Connection conn = getConnection();
		String sql = SQL.DELETE_CANAL;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			for (Canal canal : canais) {
				ps.setInt(0, canal.getId());
				ps.execute();
			}
			LOGGER.info(SUCESSO_DELETE_CANAL);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info(Messages.ERRO_EXECUCAO_DELETE);		
			e.printStackTrace();
		}
	}

}
