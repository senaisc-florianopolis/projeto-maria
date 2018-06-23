package br.senai.sc.edu.projetomaria.model;

import br.senai.sc.edu.projetomaria.resource.Messages;

public class Canal implements ValidableModel {
	
	private int id;
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "" + id;
	}

	@Override
	public boolean isValid() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
