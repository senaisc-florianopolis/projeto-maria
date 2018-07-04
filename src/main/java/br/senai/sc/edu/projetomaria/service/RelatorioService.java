package br.senai.sc.edu.projetomaria.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.io.CanalWriter;
import br.senai.sc.edu.projetomaria.io.EstimativaWritter;
import br.senai.sc.edu.projetomaria.io.HistoricoWriter;
import br.senai.sc.edu.projetomaria.io.PhaseWritter;
import br.senai.sc.edu.projetomaria.io.ProdutoWriter;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class RelatorioService {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public void exportRelatorioFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioProduto(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioCanal(Path path) throws IOException {
		CanalWriter cw = new CanalWriter();
		cw.generateRelatorio(path);
		String message = String.format(Messages.SUCESSO_RELATORIO_CANAL, path);
		LOGGER.info(message);
	}
	
	public void exportRelatorioHistorico(Path path) {
		HistoricoWriter escritor = new HistoricoWriter();
		HistoricoDAO dao = new HistoricoDAO();
		List<Historico> registros = dao.get();
		escritor.writeCsvFile(path, registros);
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioEstimativa(Path path, int periodoAnterior) {
		EstimativaWritter estimativa = new EstimativaWritter();
		estimativa.escrever(path, periodoAnterior);	
	}
	
	public void exportRelatorioPhase(Path path) {
		PhaseWritter writter = new PhaseWritter();
		writter.gerarArquivoPhase(path);
	}
}
