package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;

import br.senai.sc.edu.projetomaria.io.ProdutoWritter;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class RelatorioService {
	
	public void exportRelatorioFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioProduto(Path path) {
		ProdutoWritter writter = new ProdutoWritter();
		writter.escrever();
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioHistorico(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioEstimativa(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioPhase(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}

}
