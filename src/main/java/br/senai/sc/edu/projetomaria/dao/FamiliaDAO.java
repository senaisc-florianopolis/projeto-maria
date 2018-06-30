package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class FamiliaDAO extends AbstractDAO {

	private static final String INSERIR_FAMILIA = null;
	private static final String ATUALIZAR_FAMILIA = null;
	private static final String DELETAR_FAMILIA = null;
	private Logger LOGGER = Logger.getLogger(FamiliaDAO.class.getName());

	public ArrayList<Familia> getFamilias() {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM maria.familia;";

		ArrayList<Familia> familias = new ArrayList<>();
		try (Connection conn = getConnection()) {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Familia familia = new Familia();
				familia.setId(rs.getInt("ID_FAMILIA"));
				familia.setCodigo(rs.getString("CODIGO"));
				familias.add(familia);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.getSQLState() + " - " + e.getMessage());
		}
		return familias;
	}

	public void insert(List<Familia> familia) {
		Statement stmt = null;
		int id = getLastId();
		try (Connection conn = getConnection()) {
			for (Familia fl : familia) {
				fl.setId(id++);
				String sql = "INSERT INTO familia_comercial (ID_FAMILIA_COMERCIAL, COD_FAMILIA_COMERCIAL) VALUES ('"
						+ fl.getId() + 1 + "','" + fl.getCodigo() + "'); ";

				stmt = getConnection().createStatement();
				stmt.executeUpdate(sql);

				LOGGER.info(INSERIR_FAMILIA);
			}
		} catch (SQLException e) {
			throw new DAOLayerException(Messages.INSERIR_FAMILIA);
		}
	}

	private int getLastId() {
		Statement stmt;

		ResultSet id_reference;
		String id_reference_sql = "SELECT ID_FAMILIA_COMERCIAL from familia_comercial order by ID_FAMILIA_COMERCIAL desc limit 1;";

		try (Connection conn = getConnection()) {
			stmt = getConnection().createStatement();
			id_reference = stmt.executeQuery(id_reference_sql);
			return id_reference.getInt("ID_FAMILIA");

		} catch (SQLException e) {
			throw new DAOLayerException(Messages.INSERIR_FAMILIA);
		}

	}

	public void update(Familia familia) {
		Statement stmt = null;
		try (Connection conn = getConnection()) {
			String sql = "UPDATE familia_comercial SET COD_FAMILIA_COMERCIAL = " + "'" + familia.getCodigo() + "'"
					+ " WHERE ID_FAMILIA_COMERCIAL = " + "'" + familia.getId() + "';";

			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
			LOGGER.info(ATUALIZAR_FAMILIA);
		} catch (SQLException e) {
			throw new DAOLayerException(Messages.INSERIR_FAMILIA);

		}

	}

	public void delete(List<Familia> familias) {
		try (Connection conn = getConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM familia_comercial WHERE ID_FAMILIA_COMERCIAL = ?");
			for (Familia familia : familias) {
				ps.setInt(0, familia.getId());
				ps.executeQuery();
			}

			LOGGER.info(DELETAR_FAMILIA);
		} catch (SQLException e) {
			throw new DAOLayerException(Messages.DELETAR_FAMILIA);
		}
	}
}