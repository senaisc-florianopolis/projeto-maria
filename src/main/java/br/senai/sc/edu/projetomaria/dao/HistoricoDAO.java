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

import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.resource.SQL;

public class HistoricoDAO extends AbstractDAO {

	private static final Logger LOGGER = LogManager.getLogger();

	public List<Historico> get() {
		ArrayList<Historico> registro = new ArrayList<>();
		String query = SQL.HISTORICO_SELECT;

		try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
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
			LOGGER.error(e);
			throw new DAOLayerException(e);
		}

		return registro;
	}
	
	public int[] upsert (List<Historico> registro) {
		String sql = "INSERT INTO historico (MES_ANO,PRODUTO_SKU,ID_CANAL,QUANTIDADE) VALUES (?,?,?,?)"+
				"ON DUPLICATE KEY UPDATE MES_ANO=?,PRODUTO_SKU=?,ID_CANAL=?,QUANTIDADE= ?";
		int[] resultados = {0, 0};
		
		try (Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);) {		
			for (Historico historico : registro) {
				LOGGER.debug(historico);
				ps.setDate(1,java.sql.Date.valueOf(historico.getPeriodo()));
				ps.setInt(2,historico.getQuantidade());
				ps.setInt(3,historico.getProduto().getSku());
				ps.setInt(4,historico.getCanal().getId());
				ps.setInt(5,historico.getId());
				int retorno = ps.executeUpdate(sql);
				if (retorno == 1) {
					//resultados[0] ++;
					//resultados[0] += 1;
					resultados[0] = resultados[0] + 1;
				} else {
					resultados[1] = resultados[1] +1;
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOLayerException("Erro no Upsert do Histórico.",e);
		}

		return resultados;
	}

	public void delete(List<Historico> registro) {

		String sql = SQL.HISTORICO_DELETE;

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			for (Historico historico : registro) {
				LOGGER.debug(historico);
				ps.setInt(1, historico.getId());
				LOGGER.debug(ps);
				ps.execute();
			}
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOLayerException(e);
		}
		LOGGER.info(Messages.SUCESSO_DELETE_CANAL);
	}
}