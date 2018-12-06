package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Historico;

import br.senai.sc.edu.projetomaria.dao.FamiliaDAO;
import br.senai.sc.edu.projetomaria.dao.PhaseDAO;
import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.model.Phase;

import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class RelatorioService {
	private static final Logger LOGGER = LogManager.getLogger();

	public ServiceResponse exportarFamilia(Path path) {
//		FamiliaWriter.CSVWriter(path);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarFamilia() {
		FamiliaDAO fdao = new FamiliaDAO();
		List<Familia> familias = fdao.exportarFamilias();
		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, familias);

		return response;
	}

	public ServiceResponse exportarProduto(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarProduto() {
		ProdutoDAO pdao = new ProdutoDAO();
		List<Produto> produtos = pdao.exportarProdutos();
		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, produtos);

		return response;
	}

	public ServiceResponse exportarCanal(Path path) {
//		CanalWriter cw = new CanalWriter();
//		cw.generateRelatorio(path);
//		String message = String.format(Messages.SUCESSO_RELATORIO_CANAL, path);
//		LOGGER.info(message);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarCanal() {

		CanalDAO cdao = new CanalDAO();
		List<Canal> canais = cdao.getCanais();
		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, canais);
		
		return response;
	}

	public ServiceResponse exportarHistorico(Path path) {
//		HistoricoWriter escritor = new HistoricoWriter();
//		HistoricoDAO dao = new HistoricoDAO();
//		List<Historico> registros = dao.get();
//		escritor.writeCsvFile(path, registros);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarHistorico() {
		
		HistoricoDAO hdao = new HistoricoDAO();
		List<Historico> historicos = hdao.get();
		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, historicos);
	
		return response;
	}

	public ServiceResponse exportarPhase(Path path) {
//		PhaseWritter writter = new PhaseWritter();
//		writter.gerarArquivoPhase(path);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarPhase() {
		PhaseDAO pdao = new PhaseDAO();
		List<Phase> phases = pdao.exportarPhase();
		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, phases);

		return response;
	}

	public ServiceResponse exportarEstimativa(Path path, int periodoAnterior) {
//		EstimativaWritter estimativa = new EstimativaWritter();
//		estimativa.escrever(path, periodoAnterior);	
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarEstimativa(int periodoAnterior) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

}
