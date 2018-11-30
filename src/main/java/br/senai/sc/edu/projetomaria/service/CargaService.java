package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.exception.ServiceLayerException;
import br.senai.sc.edu.projetomaria.io.ProdutoReader;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public ServiceResponse cargaCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse cargaFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
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
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse cargaHistorico(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
