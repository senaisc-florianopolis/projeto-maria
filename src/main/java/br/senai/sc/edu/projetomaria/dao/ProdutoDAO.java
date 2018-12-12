package br.senai.sc.edu.projetomaria.dao;

import java.io.IOException;
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
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.resource.SQL;

public class ProdutoDAO extends AbstractDAO {
	private static final Logger LOGGER = LogManager.getLogger();

	public List<Produto> listarTodos() throws IOException {
		ArrayList<Produto> listaProdutos = new ArrayList<>();
		String sql = "select * from produto";
		
		try (Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Produto p = new Produto();
				p.setSku(rs.getInt("SKU"));
				p.setDescricao(rs.getString("NOME_PRODUTO"));
				p.setIdComercial(rs.getInt("ID_FAMILIA_COMERCIAL"));

				listaProdutos.add(p);
			}

			for (Produto produto : listaProdutos) {
				LOGGER.info("=== {} | {} ===", produto.toString(), Messages.PROJETO_VERSAO); //$NON-NLS-1$
			}

		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOLayerException(e);
		}
		return listaProdutos;
	}

	public List<Produto> exportarProdutos() {
		String sql = "SELECT * FROM produto ORDER BY NOME_PRODUTO;";

		List<Produto> p = new ArrayList<>();
		try (Connection conn = getConnection();
				Statement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Produto produtos = null;
				produtos = new Produto();
				produtos.setSku(rs.getInt("SKU"));
				produtos.setDescricao(rs.getString("NOME_PRODUTO"));
				produtos.setIdComercial(rs.getInt("ID_FAMILIA_COMERCIAL"));
				p.add(produtos);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOLayerException(e);
		}
		return p;
	}

	public int[] upsert(List<Produto> produto) {
		String sql = SQL.PRODUTO_UPSERT;
		
		int[] resultados = new int[2];

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			for (Produto p : produto) {
				stmt.setInt(1, p.getIdComercial());
				stmt.setString(2, p.getDescricao());
				stmt.setInt(3, p.getSku());
				stmt.setInt(4, p.getIdComercial());
				stmt.setString(5, p.getDescricao());
				LOGGER.debug(stmt);
				int retorno = stmt.executeUpdate();
				if (retorno == 1) {
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

	public void deleteProd(List<Produto> list) {
		String sql = "";
		int successes = 0;
		int total = 0;

		for (Produto p : list) {
			sql = "DELETE FROM PRODUTO WHERE SKU = " + p.getSku() + ";";
			try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
				stmt.execute();
				successes++;
			} catch (SQLException e) {
				LOGGER.debug(e);
			}
			total++;
		}
		LOGGER.info(successes + " de " + total + " " + Messages.SUCCESS_PRODUTO);
	}
}
