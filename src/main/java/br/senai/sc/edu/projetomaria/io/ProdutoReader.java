package br.senai.sc.edu.projetomaria.io;

import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class ProdutoReader {
	private static final Logger LOGGER = LogManager.getLogger();
	Produto novoProduto = null;
	Phase novoPhase = null;
	Boolean status;

	public List<Produto> lerCsvProduto(Path caminho) {
		List<Produto> produtos = new ArrayList<>();
		try (Reader leitor = Files.newBufferedReader(caminho);
				CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);) {
			for (CSVRecord gravar : conversor) {
				if (gravar.getRecordNumber() != 1) {
					String sku = gravar.get(0);
					String nomeProduto = gravar.get(1);
					String idFamiliaComercial = gravar.get(2);

					novoProduto = new Produto();
					novoProduto.setSku(Integer.parseInt(sku));
					novoProduto.setDescricao(nomeProduto);
					novoProduto.setIdComercial(Integer
							.parseInt(idFamiliaComercial));
					produtos.add(novoProduto);
				}
			}
			status = true;
		} catch (IOException e) {
			LOGGER.info(Messages.FS_ERRO_ACESSO);
			LOGGER.debug(e);
			status = false;
		}
		return produtos;
	}

	public List<Phase> lerCsvPhase(Path caminho) {
		ArrayList<Phase> phase = new ArrayList<>();

		try (Reader leitor = Files.newBufferedReader(caminho);
				CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);) {
			for (CSVRecord gravar : conversor) {
				if (gravar.getRecordNumber() != 1) {
					String skuNew = gravar.get(0);
					String skuOld = gravar.get(1);

					novoPhase = new Phase();
					novoPhase.setSkuNew(Integer.parseInt(skuNew));
					novoPhase.setSkuOld(Integer.parseInt(skuOld));

					phase.add(novoPhase);
				}
			}
			status = true;
		} catch (IOException e) {
			LOGGER.debug(e);
			status = false;
		}
		return phase;
	}

	public void cargaInicial(Path caminho) {
		ProdutoDAO dao = new ProdutoDAO();
		if (lerCsvProduto(caminho).isEmpty() && status != false) {
			LOGGER.info(Messages.ERRO_VAZIO);
		} else if (status) {
			dao.salvarProdutos(lerCsvProduto(caminho));
		}
	}

	public void updateProduto(Path caminho) {
		List<Produto> skuIgual = new ArrayList<>();
		ProdutoDAO dao = new ProdutoDAO();

		if (lerCsvProduto(caminho).isEmpty() && !status) {
			LOGGER.info(Messages.ERRO_VAZIO);
		} else if (status) {
			for (Produto exp : dao.exportarProdutos()) {
				for (Produto imp : lerCsvProduto(caminho)) {
					if (exp.getSku() == imp.getSku()) {
						skuIgual.add(imp);
					}
				}
			}
			dao.updateProduto(skuIgual);
		}
	}
	
	public void updatePhase(Path caminho){
		List<Produto> skuIgual = new ArrayList<>();
		ProdutoDAO dao = new ProdutoDAO();
	
		
	}

	public void insertPhase(Path caminho) {
		ProdutoDAO dao = new ProdutoDAO();
		
		if (lerCsvPhase(caminho).isEmpty() && !status) {
			LOGGER.info(Messages.ERRO_VAZIO);
		} else if (status) {
			dao.insertSkuPhase(lerCsvPhase(caminho));
		}
	}

	public void deleteProduto(Path caminho) {
		ProdutoDAO dao = new ProdutoDAO();

		if (lerCsvProduto(caminho).isEmpty()) {
			LOGGER.info(Messages.ERRO_VAZIO);
		} else {
			dao.deleteProd(lerCsvProduto(caminho));
		}
	}
}