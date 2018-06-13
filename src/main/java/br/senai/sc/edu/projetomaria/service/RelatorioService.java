package br.senai.sc.edu.projetomaria.service;

import java.nio.file.Path;
import java.util.List;

import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.io.HistoricoWriter;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class RelatorioService {
	
	public void exportRelatorioFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioProduto(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioHistorico(Path path) {
		HistoricoWriter escritor = new HistoricoWriter();
		HistoricoDAO dao = new HistoricoDAO();
		List<Historico> registros = dao.get();
		escritor.writeCsvFile(path, registros);
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}
	
	public void exportRelatorioEstimativa(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}

}
