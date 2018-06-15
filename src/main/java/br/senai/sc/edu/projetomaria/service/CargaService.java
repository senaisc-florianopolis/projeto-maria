package br.senai.sc.edu.projetomaria.service;

import java.util.List;
import java.io.IOException;
import java.nio.file.Path;

import br.senai.sc.edu.projetomaria.io.FamiliaDAO;
import br.senai.sc.edu.projetomaria.io.FamiliaReader;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CargaService {
	
	public void insertFamilia(Path path) {
		FamiliaReader familia = new FamiliaReader(path);
		 List<Familia> familias;
		try {
				familias = familia.readFamilia();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 

	public void updateFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteFamilia(Path path) {
		FamiliaReader familia = new FamiliaReader(path);
		List<Familia> familias = null;
		try{
				familias = familia.readFamilia();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br.senai.sc.edu.projetomaria.dao.FamiliaDAO familiaDAO = new FamiliaDAO();
		familiaDAO.delete(id);
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertProduto(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateProduto(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteProduto(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertHistorico(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateHistorico(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteHistorico(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
