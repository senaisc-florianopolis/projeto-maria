package br.senai.sc.edu.projetomaria.model;

import br.senai.sc.edu.projetomaria.resource.Messages;

public class Familia implements ValidableModel {
	
	private int codigo;
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
		
	@Override
	public String toString() {
		return "Familia [codigo=" + codigo + ", nome=" + nome + "]";
	}

	@Override
	public boolean isValid() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
