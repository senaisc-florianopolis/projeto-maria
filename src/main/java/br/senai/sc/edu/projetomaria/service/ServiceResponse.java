package br.senai.sc.edu.projetomaria.service;

import java.util.Map;

public class ServiceResponse {

	public enum STATUS {
		OK, ERROR
	};

	private STATUS status;
	private Map<String, Object> response;

	public ServiceResponse(STATUS status, Map<String, Object> response) {
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
