package br.senai.sc.edu.projetomaria.service;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.dao.FamiliaDAO;
import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.io.CanalReader;
import br.senai.sc.edu.projetomaria.io.FamiliaReader;
import br.senai.sc.edu.projetomaria.io.HistoricoReader;
import br.senai.sc.edu.projetomaria.io.ProdutoReader;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public void insertFamilia(Path path) throws SQLException {
		FamiliaReader familia = new FamiliaReader(path);
		try {
			List<Familia> list_familia = familia.readFamiliaInterable();
			FamiliaDAO dao = new FamiliaDAO();
			dao.insert(list_familia);
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void updateFamilia(Path path) throws SQLException {
		FamiliaReader familiaReader = new FamiliaReader(path);
		FamiliaDAO dao = new FamiliaDAO();
		try {
			List<Familia> familias = familiaReader.readFamilia();
			for (Familia f : familias) {
				dao.update(f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFamilia(Path path) throws SQLException {
		FamiliaReader familiaReader = new FamiliaReader(path);
		FamiliaDAO dao = new FamiliaDAO();
		try {
			List<Familia> familias = familiaReader.readFamilia();
			dao.delete(familias);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertCanal(Path path) throws SQLException {
		CanalReader canal = new CanalReader(path);
		CanalDAO dao = new CanalDAO();
		try {
			List<Canal> canais = canal.readCanalIncrement();
			dao.insert(canais);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateCanal(Path path) throws SQLException {
		CanalReader canal = new CanalReader(path);
		CanalDAO dao = new CanalDAO();
		try {
			List<Canal> canais = canal.readCanal();
			for (Canal canal2 : canais) {
				dao.update(canal2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deleteCanal(Path path) throws SQLException {
		CanalReader canal = new CanalReader(path);
		CanalDAO CanalDAO = new CanalDAO();
		try {
			List<Canal> canais = canal.readCanal();
			CanalDAO.delete(canais);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		List<Produto> produtos = reader.lerCsvProduto(path);
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvarProdutos(produtos);
	}

	public void updateProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		List<Produto> produtos = reader.lerCsvProduto(path);
		ProdutoDAO dao = new ProdutoDAO();
		dao.updateProduto(produtos);
	}

	public void deleteProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		List<Produto> produtos = reader.lerCsvProduto(path);
		ProdutoDAO dao = new ProdutoDAO();
		dao.deleteProd(produtos);
	}

	public void insertHistorico(Path path) {
		HistoricoReader leitor = new HistoricoReader();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.persist(listaRegistros);
	}

	public void updateHistorico(Path path) {
		HistoricoReader leitor = new HistoricoReader();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.update(listaRegistros);
	}

	public void deleteHistorico(Path path) {
		HistoricoReader leitor = new HistoricoReader();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.delete(listaRegistros);
	}

	public void insertPhase(Path path) {
		ProdutoReader reader = new ProdutoReader();
		List<Phase> phase = reader.lerCsvPhase(path);
		ProdutoDAO dao = new ProdutoDAO();
		dao.insertSkuPhase(phase);		
	}

	public void updatePhase(Path path) {
		throw new UnsupportedOperationException(
				Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deletePhase(Path path) {
		throw new UnsupportedOperationException(
				Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}
}
