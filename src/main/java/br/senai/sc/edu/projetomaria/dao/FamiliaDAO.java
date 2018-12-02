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
import br.senai.sc.edu.projetomaria.model.Familia;

public class FamiliaDAO extends AbstractDAO {

	private static final Logger LOGGER = LogManager.getLogger();

	public List<Familia> getFamilias() {
		String sql = "SELECT * FROM maria.familia;";
		List<Familia> familias = new ArrayList<>();
		try (Statement stmt = getConnection().createStatement()) {
			this.readCanais(stmt, sql, familias);
		} catch (SQLException e) {
			LOGGER.debug(e.getSQLState() + " - " + e.getMessage());

		}
		return familias;
	}

	public void readCanais(Statement stmt, String sql, List<Familia> familias) {
		try (ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Familia familia = new Familia();
				familia.setCodigo(rs.getInt("CODIGO"));
				familias.add(familia);
			}
		} catch (SQLException e) {
			LOGGER.debug(e.getSQLState() + " - " + e.getMessage());
		}
	}
	
	public int[] upsert (List<Familia> familias) {
		String sql = "INSERT INTO familia (COD_FAMILIA_COMERCIAL,NOME_FAMILIA_COMERCIAL) VALUES (?,?)"+
				"ON DUPLICATE KEY UPDATE COD_FAMILIA_COMERCIAL = ?, NOME_FAMILIA_COMERCIAL= ?";	
		int[] resultados = new int[2];
		resultados = new int[] {0, 0};
		
		
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
		for (Familia familia: familias) {
			    stmt.setInt(1,familia.getCodigo());
			    stmt.setString(2,familia.getNome());
			    stmt.setInt(4,familia.getCodigo());
			    stmt.setString(5,familia.getNome());
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
