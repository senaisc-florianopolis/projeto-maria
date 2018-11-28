package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
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
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
		HistoricoReader hist = new HistoricoReader();
//	    hist.leitorDeArquivos(path);
		
		List<Historico> s = hist.leitorDeArquivos(path);
		HistoricoDAO historicoDao = new HistoricoDAO();
		
		int[] result = historicoDao.upsert(s);
		
		
		ServiceResponse response = new ServiceResponse(STATUS.OK, result)
		
		
		
		//ClassLoader classLoader = CargaService.class.getClassLoader();
		//Path p = null;
		//p = Paths.get(ClassLoader.getSystemResource("dataset/arquivo.csv").toURI());
		//HistoricoDAO.class
	
	}

}
