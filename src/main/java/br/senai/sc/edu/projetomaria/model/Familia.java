package br.senai.sc.edu.projetomaria.model;

import java.util.HashSet;
import java.util.Set;

import br.senai.sc.edu.projetomaria.resource.Messages;

public class Familia implements ValidableModel {
	
	private int id;
	private String codigo;
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
	
	private Set<Familia> familia = new HashSet<Familia>();      
	
	public Set<Familia> getEmployees() {                          
		return familia;
	}

	
	@Override
	public String toString() {
		return "Familia [id=" + id + ", codigo=" + codigo + ", nome=" + nome + "]";
	}

	@Override
	public boolean isValid() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
