package br.senai.sc.edu.projetomaria.exception;

public class DAOLayerException extends RuntimeException {

	private static final long serialVersionUID = 6116333010183157487L;

	public DAOLayerException() {
		super();
	}   

	public DAOLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DAOLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOLayerException(String message) {
		super(message);
	}

	public DAOLayerException(Throwable cause) {
		super(cause);
	}

}
