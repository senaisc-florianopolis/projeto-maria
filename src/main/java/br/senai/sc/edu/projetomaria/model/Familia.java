package br.senai.sc.edu.projetomaria.model;

import br.senai.sc.edu.projetomaria.resource.Messages;

public class Familia implements ValidableModel {
	
	private int id;
	private String codigo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Familia [id=" + id + ", codigo=" + codigo + "]";
	}

	@Override
	public boolean isValid() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
