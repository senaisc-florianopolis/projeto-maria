package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.exception.ServiceLayerException;
import br.senai.sc.edu.projetomaria.io.HistoricoReader;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public ServiceResponse cargaCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
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
		HistoricoReader hist = new HistoricoReader();
		HistoricoDAO historicoDao = new HistoricoDAO();
		List<Historico> s = hist.leitorDeArquivos(path);
		LOGGER.debug("Quantidade: " + s.size());
		int[] result = null;
		try {
			result = historicoDao.upsert(s);
			
		} catch (DAOLayerException e) {
			throw new ServiceLayerException("Ocorreu um erro ao inserir ao banco de dados",e);
		}
		ServiceResponse response = new ServiceResponse(STATUS.OK, result);
		return response;
	}

}
