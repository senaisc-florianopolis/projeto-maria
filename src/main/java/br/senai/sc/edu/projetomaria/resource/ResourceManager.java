package br.senai.sc.edu.projetomaria.resource;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.exception.ResourceRequiredException;

public class ResourceManager {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final String BUNDLE_NAME_SQL = "sql"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE_SQL = ResourceBundle.getBundle(BUNDLE_NAME_SQL);
	
	private static final String BUNDLE_NAME_MESSAGES = "messages"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE_MESSAGES = ResourceBundle.getBundle(BUNDLE_NAME_MESSAGES);
	
	private static final String BUNDLE_NAME_CONFIG = "config"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE_CONFIG = ResourceBundle.getBundle(BUNDLE_NAME_CONFIG);	
	
	private ResourceManager() {}
	
	protected static String getString(String key, ResourceBundle bundle) {
		String property = null;
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			LOGGER.warn(e);
		}
		return property;
	}	

	public static String getMessage(String key) {
		String property = getString(key, RESOURCE_BUNDLE_MESSAGES);
		return property == null ? "!" + key + "!"  : property;
	}
	
	public static String getSQL(String key) {
		return getRequired(key, RESOURCE_BUNDLE_SQL);
	}	

	protected static String getRequired(String key, ResourceBundle bundle) {
		String property = getString(key, bundle);
		if (property == null) {
			String message = String.format(Messages.EXEC_ERRO_RECURSO_OBRIGATORIO, key);
			LOGGER.warn(message);
			throw new ResourceRequiredException(message); 
		}
		return property;
	}		
	
	public static String getRequiredConfig(String key) {
		return getRequired(key, RESOURCE_BUNDLE_CONFIG);
	}	
	
	public static Optional<String> getOptionalConfig(String key) {
		String property = getString(key, RESOURCE_BUNDLE_CONFIG);
		return Optional.ofNullable(property);
	}	
	
}
