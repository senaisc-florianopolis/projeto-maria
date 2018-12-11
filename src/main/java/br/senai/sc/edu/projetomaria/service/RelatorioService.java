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
import br.senai.sc.edu.projetomaria.io.CanalWriter;
import br.senai.sc.edu.projetomaria.io.FamiliaWriter;
import br.senai.sc.edu.projetomaria.io.HistoricoWriter;
import br.senai.sc.edu.projetomaria.io.PhaseWritter;
import br.senai.sc.edu.projetomaria.io.ProdutoWriter;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class RelatorioService {
	private static final Logger LOGGER = LogManager.getLogger();

	public ServiceResponse exportarFamilia(Path path) {
		FamiliaDAO fdao = new FamiliaDAO();
		List<Familia> familias = fdao.exportarFamilias();
		FamiliaWriter writer = new FamiliaWriter();
		writer.write(path, familias);
		
		return new ServiceResponse(ServiceResponse.STATUS.OK, path);
	}

	public ServiceResponse exportarFamilia() {
		FamiliaDAO fdao = new FamiliaDAO();
		List<Familia> familias = fdao.exportarFamilias();
		return new ServiceResponse(ServiceResponse.STATUS.OK, familias);
	}

	public ServiceResponse exportarProduto(Path path) {
		ProdutoDAO pdao = new ProdutoDAO();
		List<Produto> produtos = pdao.exportarProdutos();
		ProdutoWriter writer = new ProdutoWriter();
		writer.write(path, produtos);
		
		return new ServiceResponse(ServiceResponse.STATUS.OK, path);
	}

	public ServiceResponse exportarProduto() {
		ProdutoDAO pdao = new ProdutoDAO();
		List<Produto> produtos = pdao.exportarProdutos();
		return new ServiceResponse(ServiceResponse.STATUS.OK, produtos);
	}

	public ServiceResponse exportarCanal(Path path) {
//		String message = String.format(Messages.SUCESSO_RELATORIO_CANAL, path);
//		LOGGER.info(message);
		CanalDAO cdao = new CanalDAO();
		List<Canal> canais = cdao.getCanais();
		CanalWriter cw = new CanalWriter();
		cw.write(path, canais);
		
		return new ServiceResponse(ServiceResponse.STATUS.OK, path);
	}

	public ServiceResponse exportarCanal() {
		CanalDAO cdao = new CanalDAO();
		List<Canal> canais = cdao.getCanais();
		return new ServiceResponse(ServiceResponse.STATUS.OK, canais);
	}

	public ServiceResponse exportarHistorico(Path path) {
		HistoricoDAO hdao = new HistoricoDAO();
		List<Historico> historicos = hdao.get();
		HistoricoWriter escritor = new HistoricoWriter();
		escritor.write(path, historicos);
		return new ServiceResponse(ServiceResponse.STATUS.OK, path);
	}

	public ServiceResponse exportarHistorico() {
		
		HistoricoDAO hdao = new HistoricoDAO();
		List<Historico> historicos = hdao.get();
		return new ServiceResponse(ServiceResponse.STATUS.OK, historicos);
	}

	public ServiceResponse exportarPhase(Path path) {
		PhaseDAO pdao = new PhaseDAO();
		List<Phase> phases = pdao.exportarPhase();
		PhaseWritter writter = new PhaseWritter();
		writter.write(path, phases);
		return new ServiceResponse(ServiceResponse.STATUS.OK, path);
	}

	public ServiceResponse exportarPhase() {
		PhaseDAO pdao = new PhaseDAO();
		List<Phase> phases = pdao.exportarPhase();
		return new ServiceResponse(ServiceResponse.STATUS.OK, phases);
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
