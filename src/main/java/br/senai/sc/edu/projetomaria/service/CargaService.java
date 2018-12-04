package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.dao.FamiliaDAO;
import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.dao.PhaseDAO;
import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.exception.ServiceLayerException;
import br.senai.sc.edu.projetomaria.io.CanalReader;
import br.senai.sc.edu.projetomaria.io.FamiliaReader;
import br.senai.sc.edu.projetomaria.io.HistoricoReader;
import br.senai.sc.edu.projetomaria.io.PhaseReader;
import br.senai.sc.edu.projetomaria.io.ProdutoReader;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.service.ServiceResponse.STATUS;

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
		FamiliaReader familiaReader = new FamiliaReader();
		FamiliaDAO familiaDAO = new FamiliaDAO();

		List<Familia> familias = familiaReader.leitorFamilia(path);
		int[] array = familiaDAO.upsert(familias);

		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, array);
		return response;
	}

	public ServiceResponse cargaProduto(Path path) {
		ProdutoReader produtoReader = new ProdutoReader();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		List<Produto> produtos = produtoReader.lerCsvProduto(path);
		int[] array = produtoDAO.upsert(produtos);
		try {
			produtoDAO.upsert(produtos);
		} catch (DAOLayerException e) {
			throw new ServiceLayerException("Erro: DAO", e);
		}

		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, array);
		return response;
	}

	public ServiceResponse cargaPhase(Path path) {
		PhaseReader phaseReader = new PhaseReader();
		PhaseDAO phaseDAO = new PhaseDAO();

		List<Phase> skuPhase = phaseReader.lerCsvPhase(path);

		int[] array = phaseDAO.upsertSkuPhase(skuPhase);
		try {
			phaseDAO.upsertSkuPhase(skuPhase);
		} catch (DAOLayerException e) {
			throw new ServiceLayerException("Erro: DAO", e);
		}

		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, array);
		return response;
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
			throw new ServiceLayerException("Ocorreu um erro ao inserir ao banco de dados", e);
		}
		ServiceResponse response = new ServiceResponse(STATUS.OK, result);
		return response;
	}

}
