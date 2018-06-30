package br.senai.sc.edu.projetomaria.model;

import br.senai.sc.edu.projetomaria.resource.Messages;

public class Produto implements ValidableModel {
	
	private int sku;
	private String descricao;
	private int idComercial;
	
	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getIdComercial() {
		return idComercial;
	}

	public void setIdComercial(int idComercial) {
		this.idComercial = idComercial;
	}

	@Override
	public String toString() {
		return "Produto [sku=" + sku + ", descricao=" + descricao + "]";
	}

	@Override
	public boolean isValid() {
		boolean ok = true;
		if (this.descricao != null && this.descricao.isEmpty()) {
			ok = false;
		}
		return ok;
	}

}
