package br.senai.sc.edu.projetomaria.resource;

import java.util.Optional;

public abstract class Config {
	
	// Definições de Banco de Dados
	public static final String DB_HOSTNAME = ResourceManager.getRequiredConfig("db.hostname"); //$NON-NLS-1$
	public static final String DB_PORT = ResourceManager.getRequiredConfig("db.port"); //$NON-NLS-1$
	public static final String DB_USERNAME = ResourceManager.getRequiredConfig("db.username"); //$NON-NLS-1$
	public static final Optional<String> DB_PASSWORD = ResourceManager.getOptionalConfig("db.password"); //$NON-NLS-1$
	public static final String DB_DATABASE = ResourceManager.getRequiredConfig("db.database"); //$NON-NLS-1$	
	
	private Config() {}
	
}
