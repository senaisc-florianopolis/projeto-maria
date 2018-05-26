package br.senai.sc.edu.projetomaria.exception;

public class ServiceLayerException extends RuntimeException {

	private static final long serialVersionUID = 6116333010183157487L;

	public ServiceLayerException() {
		super();
	}

	public ServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLayerException(String message) {
		super(message);
	}

	public ServiceLayerException(Throwable cause) {
		super(cause);
	}

}
