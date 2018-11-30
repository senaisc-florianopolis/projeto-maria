package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.FamiliaDAO;
import br.senai.sc.edu.projetomaria.io.FamiliaReader;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public ServiceResponse cargaCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}
	
	public ServiceResponse cargaFamilia(Path path) {
		FamiliaReader familiaReader = new FamiliaReader();
		FamiliaDAO familiaDAO = new FamiliaDAO();
		
		List<Familia> familias = familiaReader.leitorFamilia(path);
		int[] array = familiaDAO.upsert(familias);
		
		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, array);
		return response;
	}

	public ServiceResponse cargaProduto(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse cargaPhase(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse cargaHistorico(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
