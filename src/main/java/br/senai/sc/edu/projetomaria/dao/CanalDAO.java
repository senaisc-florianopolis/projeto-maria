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
import br.senai.sc.edu.projetomaria.model.Familia;
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

	private Logger LOGGER = Logger.getLogger(CanalDAO.class.getName());

	public ArrayList<Canal> getCanais() throws SQLException {
		String sql = SQL.GET_CANAL;
		ArrayList<Canal> canais = new ArrayList<>();
		try (Statement stmt = getConnection().createStatement()){
			ResultSet rs = stmt.executeQuery(sql);
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

	public void insert(List<Canal> canal) throws SQLException {
		String sql =  SQL.INSERT_CANAL;
		try (PreparedStatement stmt =  getConnection().prepareStatement(sql)){
			for (Canal cn : canal) {
				stmt.setInt(1, cn.getId());
				stmt.setString(2, cn.getDescricao());
				stmt.execute();				
				LOGGER.info(Messages.SUCESSO_CANAL_INSERIR);
			}
		} catch (SQLException e) {
			LOGGER.warning(Messages.ERRO_CANAL_INSERIR);
		}
	}

	public void update(Canal canal) throws SQLException {
		String sql = SQL.UPDATE_CANAL;
		try (PreparedStatement stmt =  getConnection().prepareStatement(sql)){
			stmt.setString(1, canal.getDescricao());
			stmt.setInt(2, canal.getId());
			stmt.execute();
			LOGGER.info(Messages.SUCESSO_CANAL_ATUALIZAR);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.warning(Messages.ERRO_CANAL_ATUALIZAR);
		}

	}
	public void delete(List<Canal> canais) throws SQLException {
		Connection conn = getConnection();
		String sql = SQL.DELETE_CANAL;
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			for (Canal canal : canais) {
				ps.setInt(1, canal.getId());
				ps.execute();
			}
			LOGGER.info(Messages.SUCESSO_CANAL_DELETAR);
		} catch (SQLException e1) {
			LOGGER.warning(Messages.ERRO_CANAL_DELETAR);
			e1.printStackTrace();
		}finally {
			conn.close();
		}
	}
}
