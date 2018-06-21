package br.senai.sc.edu.projetomaria.service;

import java.util.List;
import java.io.IOException;
import java.nio.file.Path;

import br.senai.sc.edu.projetomaria.dao.CanalDao;
import br.senai.sc.edu.projetomaria.io.CanalReader;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CargaService {

	public void insertFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}

	public void updateFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertCanal(Path path) {
		CanalReader canal = new CanalReader(path);
		try {
			canal.readCanal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteCanal(Path path) {
		CanalReader canal = new CanalReader(path);
		List<Canal> canais = null;
		try {
			canais =canal.readCanal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CanalDao canalDAO = new CanalDao();
		canalDAO.delete(canais);
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
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
