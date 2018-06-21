package br.senai.sc.edu.projetomaria.service;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.io.CanalWriter;
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
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioEstimativa(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
} 
