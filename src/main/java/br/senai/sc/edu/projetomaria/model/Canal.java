package br.senai.sc.edu.projetomaria.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.resource.Messages;

public class Canal implements ValidableModel {

	private static final Logger LOGGER = LogManager.getLogger();
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
		boolean teste = false;

		if (this.descricao.contains("^[a-Z]")) {
			assert true;
		} else {
			LOGGER.error(Messages.ERROR_DESCRICAO_CARACTER);
		}

		if (this.descricao != null) {
			assert true;
		} else {
			LOGGER.error(Messages.ERROR_DESCRICAO_NULO);
		}

		if (this.descricao.length() > 255) {
			assert true;
		} else {
			LOGGER.error(Messages.ERROR_DESCRICAO_TAMANHO);
		}

		return teste;
	}

}
