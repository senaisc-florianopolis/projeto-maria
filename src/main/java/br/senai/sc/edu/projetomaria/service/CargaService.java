package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.io.CanalReader;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public ServiceResponse cargaCanal(Path path) {

		CanalReader canalReader = new CanalReader(path);
		CanalDAO canalDao = new CanalDAO();
		List<Canal> canais = canalReader.readCanal();
		int[] result = canalDao.upsertCanal(canais);

		ServiceResponse serviceResponse = new ServiceResponse(ServiceResponse.STATUS.OK, result);

		return serviceResponse;

	}

	public ServiceResponse cargaFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
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
