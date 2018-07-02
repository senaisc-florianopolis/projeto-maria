package br.senai.sc.edu.projetomaria.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
						status = true;
						novoProduto = new Produto();
						novoProduto.setSku(Integer.parseInt(sku));
						novoProduto.setDescricao(nomeProduto);
						novoProduto.setIdComercial(Integer.parseInt(idFamiliaComercial));
						produtos.add(novoProduto);
				}				
			}
			
		} catch (IOException e) {
			status = false;
			LOGGER.info(Messages.FS_ERRO_ACESSO);
			LOGGER.debug(e);
		}
		return produtos;
	}

	public List<Phase> lerCsvPhase(Path caminho) {
		List<Phase> phase = new ArrayList<>();

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
			status = false;
			LOGGER.info(Messages.FS_ERRO_ACESSO);
			LOGGER.debug(e);
		}
		return phase;
	}
}