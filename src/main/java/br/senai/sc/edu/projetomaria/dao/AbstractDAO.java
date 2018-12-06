package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.exception.ResourceRequiredException;
import br.senai.sc.edu.projetomaria.resource.Config;
import br.senai.sc.edu.projetomaria.resource.Messages;

public abstract class AbstractDAO {

	protected String getURL() {
		String hostname = Config.DB_HOSTNAME;
		String database = Config.DB_DATABASE;
		String port = Config.DB_PORT;
		String format = "jdbc:mysql://%1s:%2s/%3s?useAffectedRows=true";
		return String.format(format, hostname, port, database);
	}

	protected Connection getConnection() {
		String url;
		String username;
		Optional<String> password;
		try {
			url = this.getURL();
			username = Config.DB_USERNAME;
			password = Config.DB_PASSWORD;
		} catch (ResourceRequiredException e) {
			throw new DAOLayerException(Messages.BD_ERRO_CONEXAO, e);
		}
		Properties props = new Properties();
		props.put("user", username);
		props.put("autoReconnect", "true");
		if (password.isPresent()) {
			props.put("password", password.get());
		}
		try {
			return DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			throw new DAOLayerException(Messages.BD_ERRO_CONEXAO, e);
		}
	}

}
