package br.senai.sc.edu.projetomaria.service;


import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.dao.FamiliaDAO;
import br.senai.sc.edu.projetomaria.io.CanalReader;
import br.senai.sc.edu.projetomaria.io.FamiliaReader;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Messages;


public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public void insertFamilia(Path path) throws SQLException {
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
		FamiliaReader familia = new FamiliaReader(path);
		try {
			List<Familia> list_familia = familia.readFamilia();
			FamiliaDAO dao = new FamiliaDAO();
			dao.insert(list_familia);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteFamilia(Path path) {
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
		FamiliaReader familia = new FamiliaReader(path);
		List<Familia> familias = null;

		try {
			familias = familia.readFamilia();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FamiliaDAO familiaDAO = new FamiliaDAO();
		familiaDAO.delete(familias);
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
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
		CanalDAO CanalDAO = new CanalDAO();
		CanalDAO.delete(canais);
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
