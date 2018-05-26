package br.senai.sc.edu.projetomaria.exception;

public class IOLayerException extends RuntimeException {

	private static final long serialVersionUID = 6116333010183157487L;

	public IOLayerException() {
		super();
	}

	public IOLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IOLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public IOLayerException(String message) {
		super(message);
	}

	public IOLayerException(Throwable cause) {
		super(cause);
	}

}
