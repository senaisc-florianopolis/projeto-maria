package br.senai.sc.edu.projetomaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.resource.Config;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.resource.ResourceManager;

public abstract class AbstractDAO {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	protected String getURL() {
		String hostname = Config.DB_HOSTNAME;
		String database = Config.DB_DATABASE;
		String port = Config.DB_PORT;
		String format = "jdbc:mysql://%1s:%2s/%3s";
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
		} catch (RuntimeException e) {
			//TODO: ajustar para o catch correto e o throw também
			throw new RuntimeException(ResourceManager.getMessage(Messages.BD_ERRO_CONEXAO));
		}
		Properties props = new Properties();
	    props.put("username", username);
	    props.put("autoReconnect", "true");	    
	    if (password.isPresent()) {
	    	props.put("password", password.get());
	    }
	    try {
			return DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			LOGGER.error(e);
			//TODO: ajustar exception
			throw new RuntimeException(ResourceManager.getMessage(Messages.BD_ERRO_CONEXAO));
		}
	}

}
