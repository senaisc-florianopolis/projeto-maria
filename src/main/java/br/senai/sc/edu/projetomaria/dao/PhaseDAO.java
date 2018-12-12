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
import br.senai.sc.edu.projetomaria.resource.SQL;

public class PhaseDAO extends AbstractDAO {

	private static final Logger LOGGER = LogManager.getLogger();

	public int[] upsertSkuPhase(List<Phase> skuPhase) {
		String sql = SQL.PHASE_UPSERT;
	
		int[] resultados = new int[2];
		
		for (Phase p : skuPhase) {
			
			try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
				stmt.setInt(1, p.getSkuNew());
				stmt.setInt(2, p.getSkuOld());
				stmt.setInt(3, p.getSkuNew());
				stmt.setInt(4, p.getSkuOld());
				int retorno = stmt.executeUpdate();
				if (retorno == 1) {
					resultados[0] = resultados[0] + 1;
				} else {
					resultados[1] = resultados[1] + 1;
				}
			} catch (SQLException e) {
				LOGGER.debug(e);
				throw new DAOLayerException(e);
			}
		}
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
