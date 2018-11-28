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

import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Messages;

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
	
	public void upsert (List<Familia> familias) throws SQLException{
		String sql = "";
		int successes = 0;
		int total = 0;			
		
		for (Familia familia: familias) {
		sql = "INSERT INTO familia (COD_FAMILIA_COMERCIAL,NOME_FAMILIA_COMERCIAL) VALUES (?,?)"+
		"ON DUPLICATE KEY UPDATE COD_FAMILIA_COMERCIAL = ?, NOME_FAMILIA_COMERCIAL= ?";	
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			    stmt.setInt(1,familia.getCodigo());
			    stmt.setString(2,familia.getNome());
			    stmt.setInt(4,familia.getCodigo());
			    stmt.setString(5,familia.getNome());
				stmt.executeUpdate(sql);
				successes++;
			} catch (SQLException e) {
				LOGGER.debug(e);
			}
		total++;
	}
		LOGGER.info(successes + " de " + total + " " + Messages.SUCCESS_FAMILIA);
	}

}
