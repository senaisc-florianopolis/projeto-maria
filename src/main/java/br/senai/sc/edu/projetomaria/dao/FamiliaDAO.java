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
	
	public void insert (List<Familia> familia) {
		Statement stmt = null;
		int rs;

		for (Familia fl: familia) {
			String sql = "INSERT INTO familia_comercial (ID_FAMILIA_COMERCIAL, COD_FAMILIA_COMERCIAL) VALUES ('" + fl.getId() + "','" + fl.getCodigo()+ "'); ";
			System.out.println(sql);
			try {
				stmt = getConnection().createStatement();
				rs = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Message for user??
				System.out.println(e);
			}
		}
	}

	public void update(Familia familia) {
		Statement stmt = null;
		int rs;
		String sql = "UPDATE familia_comercial SET COD_FAMILIA_COMERCIAL = " + "'" + familia.getCodigo() + "'" +
				" WHERE ID_FAMILIA_COMERCIAL = " +"'" + familia.getId() + "';" ;
		System.out.println(sql);
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeUpdate(sql);
		}	catch (SQLException e) {
			// TODO Message for user??

		}

	}


	public void delete(List<Familia>familias) {
		Statement stmt = null;
		int rs;
		for (Familia fl: familias) {
			String sql = "DELETE FROM familia_comercial WHERE ID_FAMILIA_COMERCIAL = " + fl.getId() + ";" ;
			System.out.println(sql);
			try {
				stmt = getConnection().createStatement();
				rs = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Message for user??
				System.out.println(e);
			}
		}
	}
}