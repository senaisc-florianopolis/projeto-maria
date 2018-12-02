package br.senai.sc.edu.projetomaria.model;

import java.time.LocalDate;

public class Historico implements ValidableModel {

	private LocalDate periodo;
	private int quantidade;
	private Produto produto;
	private Canal canal;

	public LocalDate getPeriodo() {
		return periodo;
	}

	public void setPeriodo(LocalDate periodo) {
		this.periodo = periodo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	@Override
	public String toString() {
		return "Historico [periodo=" + periodo + ", quantidade=" + quantidade + ", produto=" + produto
				+ ", canal=" + canal + "]";
	}

	@Override
	public boolean isValid() {
		boolean ok = true;
		if (this.produto.getSku() == 0 || this.canal.getId() == 0 || this.periodo == null) {
			ok = false;
		}
		return ok;
	}
}
