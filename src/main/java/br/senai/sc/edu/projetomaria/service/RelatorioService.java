package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class RelatorioService {
	private static final Logger LOGGER = LogManager.getLogger();

	public ServiceResponse exportarFamilia(Path path) {
//		FamiliaWriter.CSVWriter(path);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}
	
	public ServiceResponse exportarFamilia() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarProduto(Path path) {
		ProdutoDAO pdao = new ProdutoDAO();
		List<Produto> produtos = pdao.exportarProdutos();
		ServiceResponse response = new ServiceResponse(ServiceResponse.STATUS.OK, produtos);
		
		return response;
	}
	
	public ServiceResponse exportarProduto() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarCanal(Path path) {
//		CanalWriter cw = new CanalWriter();
//		cw.generateRelatorio(path);
//		String message = String.format(Messages.SUCESSO_RELATORIO_CANAL, path);
//		LOGGER.info(message);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarCanal() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarHistorico(Path path) {
//		HistoricoWriter escritor = new HistoricoWriter();
//		HistoricoDAO dao = new HistoricoDAO();
//		List<Historico> registros = dao.get();
//		escritor.writeCsvFile(path, registros);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarHistorico() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public ServiceResponse exportarPhase(Path path) {
//		PhaseWritter writter = new PhaseWritter();
//		writter.gerarArquivoPhase(path);
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}
	
	public ServiceResponse exportarPhase() {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
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
