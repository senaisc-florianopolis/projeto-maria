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
import br.senai.sc.edu.projetomaria.model.Produto;

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
		String sql = "INSERT INTO familia_comercial (COD_FAMILIA_COMERCIAL,NOME_FAMILIA_COMERCIAL) VALUES (?,?)"
	+"ON DUPLICATE KEY UPDATE COD_FAMILIA_COMERCIAL = ?, NOME_FAMILIA_COMERCIAL = ?";	
		int[] resultados = new int[2];
		resultados = new int[] {0, 0};
		
		
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
		for (Familia familia: familias) {
			    stmt.setInt(1,familia.getCodigo());
			    stmt.setString(2,familia.getNome());
			    stmt.setInt(3,familia.getCodigo());
			    stmt.setString(4,familia.getNome());
				int retorno = stmt.executeUpdate();
				if(retorno == 1) {
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
	
	public List<Familia> exportarFamilias() {
		String sql = "SELECT * FROM familia ORDER BY COD_FAMILIA_COMERCIAL;";

		List<Familia> f = new ArrayList<>();
		try (Connection conn = getConnection();
				Statement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Familia familias = null;
				familias = new Familia();
				familias.setCodigo(rs.getInt("COD_FAMILIA_COMERCIAL"));
				familias.setNome(rs.getString("NOME_FAMILIA_COMERCIAL"));
				f.add(familias);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return f;
	}

}
