package br.senai.sc.edu.projetomaria.resource;

import java.util.Optional;

public abstract class Config {
	
	// Definições de Banco de Dados
	public static final String DB_HOSTNAME;
	public static final String DB_PORT;
	public static final String DB_USERNAME;
	public static final Optional<String> DB_PASSWORD = ResourceManager.getOptionalConfig("db.password"); //$NON-NLS-1$
	public static final String DB_DATABASE = ResourceManager.getRequiredConfig("db.database"); //$NON-NLS-1$
	//Definições de separação do CSV
	public static final char CSV_DELIMITADOR;
	
	static {
		DB_HOSTNAME = ResourceManager.getRequiredConfig("db.hostname"); //$NON-NLS-1$
		DB_PORT = ResourceManager.getRequiredConfig("db.port"); //$NON-NLS-1$
		DB_USERNAME = ResourceManager.getRequiredConfig("db.username"); //$NON-NLS-1$
		CSV_DELIMITADOR = ResourceManager.getRequiredConfig("csv.delimitador").charAt(0); //$NON-NLS-1$
	}
	
	private Config() {}
	
}
