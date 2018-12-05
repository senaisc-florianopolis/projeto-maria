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
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class PhaseDAO extends AbstractDAO {

	private static final Logger LOGGER = LogManager.getLogger();

	public int[] upsertSkuPhase(List<Phase> skuPhase) {
		String sql = "";
		int successes = 0;
		int[] resultados = { 0, 0 };
		int total = 0;
		for (Phase p : skuPhase) {
			sql = "INSERT INTO sku_phase(SKU_PHASE_IN,SKU_PHASE_OUT) VALUES (?,? )"
		+ "ON DUPLICATE KEY UPDATE SKU_PHASE_IN = ?, SKU_PHASE_OUT = ?";
			try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
				stmt.setInt(1, p.getSkuNew());
				stmt.setInt(2, p.getSkuOld());
				stmt.setInt(3, p.getSkuNew());
				stmt.setInt(4, p.getSkuOld());
				int retorno = stmt.executeUpdate();
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

	public List<Phase> exportarPhase() {
		String sql = "SELECT * FROM SKU_PHASE;";

		List<Phase> ph = new ArrayList<>();
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
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

}
