package br.senai.sc.edu.projetomaria.model;

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
		return this.descricao != null;
	}

}
