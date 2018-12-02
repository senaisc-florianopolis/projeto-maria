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
	
	public int[] upsert (List<Historico> historicos) {
		String sql = "INSERT INTO historico (MES_ANO,PRODUTO_SKU,ID_CANAL,QUANTIDADE) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE MES_ANO=?,PRODUTO_SKU=?,ID_CANAL=?,QUANTIDADE=?";
		int[] resultados = {0, 0};
		
		try (Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);) {		
			for (Historico historico : historicos) {
				ps.setDate(1,java.sql.Date.valueOf(historico.getPeriodo()));
				ps.setInt(2,historico.getProduto().getSku());
				ps.setInt(3,historico.getCanal().getId());
				ps.setInt(4,historico.getQuantidade());
				ps.setDate(5,java.sql.Date.valueOf(historico.getPeriodo()));
				ps.setInt(6,historico.getProduto().getSku());
				ps.setInt(7,historico.getCanal().getId());
				ps.setInt(8,historico.getQuantidade());
				LOGGER.debug(ps);
				int retorno = ps.executeUpdate();
				if (retorno == 1) {
					resultados[0] = resultados[0] + 1;
				} else {
					resultados[1] = resultados[1] +1;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			LOGGER.error(e);
			throw new DAOLayerException("Erro no Upsert do Histórico.",e);
		}

		return resultados;
	}
	
	public int[] upsert (List<Historico> registro) {
		String sql = "INSERT INTO familia (MES_ANO = ?, PRODUTO_SKU = ?, ID_CANAL = ?, QUANTIDADE = ?) VALUES (?,?,?,?)"+
				"ON DUPLICATE KEY UPDATE MES_ANO = ?, PRODUTO_SKU = ?, ID_CANAL = ?, QUANTIDADE = ?";	
		int[] resultados = new int[2];
		resultados = new int[] {0, 0};
		
		
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
		for (Historico historico: registro) {
			    stmt.setDate(1, java.sql.Date.valueOf(historico.getPeriodo()));
			    stmt.setObject(2,historico.getProduto().getSku());
			    stmt.setInt(4,historico.getCanal().getId());
			    stmt.setInt(5,historico.getQuantidade());
			    stmt.setDate(6, java.sql.Date.valueOf(historico.getPeriodo()));
			    stmt.setObject(7,historico.getProduto().getSku());
			    stmt.setInt(8,historico.getCanal().getId());
			    stmt.setInt(9,historico.getQuantidade());
				int retorno = stmt.executeUpdate(sql);
				if(retorno == 1) {
					// resultado[0]++;
					// resuldato[0] += 1;
					resultados[0] = resultados[0] + 1;
				} else {
					resultados[1] = resultados[1] + 1;
				}
		}
				
			} catch (SQLException e) {
				LOGGER.error(e);
				throw new DAOLayerException(e);
			}
		return resultados;
	}
}