package br.senai.sc.edu.projetomaria.service;

public class ServiceResponse {

	public enum STATUS {
		OK, ERROR
	};

	private STATUS status;
	private Object response;

	public ServiceResponse(STATUS status, Object response) {
		this.status = status;
		this.response = response;
	}

	public STATUS getStatus() {
		return status;
	}

	public Object getResponse() {
		return response;
	}

}
