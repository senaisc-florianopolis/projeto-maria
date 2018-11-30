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
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class ProdutoDAO extends AbstractDAO {
	private static final Logger LOGGER = LogManager.getLogger();
	int total;
	private String path;

	public List<Produto> listarTodos() throws IOException {
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
		try {
			String sql = "select * from produto";
			Statement stmt = getConnection().createStatement();

			ResultSet rs = stmt.executeQuery(sql);

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
			e.printStackTrace();
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
		}
		return p;
	}

	public List<Phase> exportarPhase() {
		String sql = "SELECT * FROM SKU_PHASE;";

		List<Phase> ph = new ArrayList<>();
		try (Connection conn = getConnection();
				Statement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Phase phase = null;
				phase = new Phase();
				phase.setSkuNew(Integer.parseInt(rs.getString("SKU_PHASE_IN")));
				phase.setSkuOld(Integer.parseInt(rs.getString("SKU_PHASE_OUT")));
				ph.add(phase);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return ph;
	}

	public void salvarProdutos(List<Produto> list) {
		String sql = "";
		int successes = 0;
		total = 0;

		for (Produto p : list) {
			sql = "INSERT INTO PRODUTO(" + "SKU," + "NOME_PRODUTO," + "ID_FAMILIA_COMERCIAL) VALUES (" + p.getSku()
					+ ",'" + p.getDescricao() + "'," + p.getIdComercial() + ");";

			try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
				stmt.execute();
				successes++;
			} catch (SQLException e) {
				LOGGER.error(e);
			}
			total++;
		}
		LOGGER.info(successes + " de " + total + " " + Messages.SUCCESS_PRODUTO);
	}

	public int[] upsert(List<Produto> produto) {
		String sql = "INSERT INTO produto (COD_FAMILIA_COMERCIAL,NOME_PRODUTO,SKU) VALUES (?,?,?)"
				+ "ON DUPLICATE KEY UPDATE COD_FAMILIA_COMERCIAL = ?, NOME_PRODUTO = ?, SKU = ?";
		;
		int[] resultados = new int[2];

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			for (Produto p : produto) {
				stmt.setInt(1, p.getIdComercial());
				stmt.setString(2, p.getDescricao());
				stmt.setInt(3, p.getSku());
				stmt.setInt(4, p.getIdComercial());
				stmt.setString(5, p.getDescricao());
				stmt.setInt(6, p.getSku());
				LOGGER.debug(stmt);
				int retorno = stmt.executeUpdate();
				if (retorno == 1) {
					resultados[0] = resultados[0] + 1;
				} else {
					resultados[1] = resultados[0] + 1;
				}

			}
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOLayerException(e);
		}
		return resultados;
	}

	public int[] upsertSkuPhase(List<Phase> skuPhase) {
		String sql = "";
		int successes = 0;
		int[] resultados = { 0, 0 };
		total = 0;
		for (Phase p : skuPhase) {
			sql = "INSERT INTO sku_phase(" + "SKU_PHASE_IN," + "SKU_PHASE_OUT) VALUES (\" + p.getSkuNew() + \",\"\r\n"
					+ "	+ p.getSkuOld() + \");\" " + "ON DUPLICATE KEY UPDATE SKU_PHASE_IN = ?, SKU_PHASE_OUT = ?";
			try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
				stmt.execute();
				int retorno = stmt.executeUpdate(sql);
				if (retorno == 1) {
					resultados[1] += 1;
					resultados[0] = resultados[0] + 1;
				} else {
					resultados[1] = resultados[1] + 1;
				}
				successes++;
			} catch (SQLException e) {
				if (e.getErrorCode() == 1062) {
					LOGGER.info("registros duplicados. Retire-os e tente novamente. Mensagem SQL = " + e.getMessage());
				}
				if (e.getErrorCode() == 1) {
					LOGGER.info("linha em branco. Ajuste e tente novamente. Mensagem SQL = " + e.getMessage());
				}
				if (e.getErrorCode() == 2) {
					LOGGER.info("Coluna em branco. Ajuste e tente novamente. Mensagem SQL = " + e.getMessage());
				} else {
					LOGGER.info(
							"Registro fora do Padrão. Retire-os e tente novamente. Mensagem SQL = " + e.getMessage());
				}
				LOGGER.debug(e);
				throw new DAOLayerException(e);
			}
			total++;
		}
		LOGGER.info(successes + " de " + total + " " + Messages.SUCCESS_PRODUTO);
		return resultados;
	}

}