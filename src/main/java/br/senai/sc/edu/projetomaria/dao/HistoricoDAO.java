package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.SQL;

public class HistoricoDAO extends AbstractDAO {

	private static final Logger LOGGER = LogManager.getLogger();

	public List<Historico> get() {
		ArrayList<Historico> registro = new ArrayList<>();
		String query = SQL.HISTORICO_SELECT;

		Statement st;

		try {
			st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Historico h = new Historico();
				Canal canal = new Canal();
				canal.setId(rs.getInt("ID_CANAL"));
				h.setCanal(canal);
				h.setId(rs.getInt("ID_HISTORICO"));
				Produto produto = new Produto();
				produto.setSku(rs.getInt("PRODUTO_SKU"));
				h.setProduto(produto);
				h.setPeriodo(rs.getDate("MES_ANO").toLocalDate());
				h.setQuantidade(rs.getInt("QUANTIDADE"));
				registro.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return registro;
	}

	public void persist(List<Historico> registro) {

		String sql = SQL.HISTORICO_INSERT;


		try (Connection conn = getConnection()) {
			PreparedStatement ps = null;
			ps = getConnection().prepareStatement(sql);
			for (Historico historico : registro) {
				LOGGER.debug(historico);
				ps.setDate(1, java.sql.Date.valueOf(historico.getPeriodo()));
				ps.setInt(2, historico.getQuantidade());
				ps.setInt(3, historico.getProduto().getSku());
				ps.setInt(4, historico.getCanal().getId());
				LOGGER.debug(ps);
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}


	public void update(List<Historico> registro) {

		String sql = SQL.HISTORICO_UPDATE;

		try (Connection conn = getConnection() ) {
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			for (Historico historico : registro) {
				LOGGER.debug(historico);
				ps.setDate(1, java.sql.Date.valueOf(historico.getPeriodo()));
				ps.setInt(2, historico.getQuantidade());
				ps.setInt(3, historico.getProduto().getSku());
				ps.setInt(4, historico.getCanal().getId());
				ps.setInt(5, historico.getId());
				LOGGER.debug(ps);
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(List<Historico> registro) {

		String sql = SQL.HISTORICO_DELETE;

		try (Connection conn = getConnection()) {
			PreparedStatement ps = null;
			ps = getConnection().prepareStatement(sql);
			for (Historico historico : registro) {
				LOGGER.debug(historico);
				ps.setInt(1, historico.getId());
				LOGGER.debug(ps);
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}