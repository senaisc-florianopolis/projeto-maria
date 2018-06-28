package br.senai.sc.edu.projetomaria.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.io.ProdutoReader;
import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.io.LeitorCsv;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.io.CanalReader;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;



public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public void insertFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO); 
	}

	public void updateFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertCanal(Path path) {
		CanalReader canal = new CanalReader(path);
		try {
			canal.readCanal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteCanal(Path path) {
		CanalReader canal = new CanalReader(path);
		List<Canal> canais = null;
		try {
			canais =canal.readCanal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CanalDAO CanalDAO = new CanalDAO();
		CanalDAO.delete(canais);
		//throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		reader.cargaInicial(path);
	}

	public void updateProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		reader.updateProduto(path);
	}

	public void deleteProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		reader.deleteProduto(path);
	}

	public void insertHistorico(Path path) {
		LeitorCsv leitor = new LeitorCsv();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.persist(listaRegistros);
	}

	public void updateHistorico(Path path) {
		LeitorCsv leitor = new LeitorCsv();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.update(listaRegistros);
	}

	public void deleteHistorico(Path path) {
		LeitorCsv leitor = new LeitorCsv();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.delete(listaRegistros);
	}
	
	public void insertPhase(Path path) {
		ProdutoReader reader = new ProdutoReader();
		reader.insertPhase(path);
	}

	public void updatePhase(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deletePhase(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}
}
