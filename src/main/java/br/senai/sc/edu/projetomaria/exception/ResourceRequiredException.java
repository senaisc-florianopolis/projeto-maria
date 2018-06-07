package br.senai.sc.edu.projetomaria.exception;

public class ResourceRequiredException extends RuntimeException {

	private static final long serialVersionUID = 6116333010183157487L;

	public ResourceRequiredException() {
		super();
	}

	public ResourceRequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceRequiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceRequiredException(String message) {
		super(message);
	}

	public ResourceRequiredException(Throwable cause) {
		super(cause);
	}

}
