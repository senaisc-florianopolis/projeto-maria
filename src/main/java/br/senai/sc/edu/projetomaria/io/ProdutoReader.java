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

import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class ProdutoReader {
	private static final Logger LOGGER = LogManager.getLogger();
	Produto novoProduto = null;

	public List<Produto> lerCsvProduto(Path caminho) throws Exception {
		int contErrosP = 0;

		List<Produto> produtos = new ArrayList<>();
		List<String> erros = new ArrayList<>();
		try (Reader leitor = Files.newBufferedReader(caminho);
				CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);) {
			for (CSVRecord ler : conversor) {
				if (ler.getRecordNumber() != 1) {
					
					String idFamiliaComercial = ler.get(0);
					String nomeProduto = ler.get(1);
					String sku = ler.get(2);

					boolean idFamiliaComercialR = idFamiliaComercial.matches("^[0-9]{1,20}$");
					boolean nomeProdutoR = nomeProduto.matches("^.{1,255}$");
					boolean skuR = sku.matches("^[0-9]{1,20}$");

					if (skuR && nomeProdutoR && idFamiliaComercialR) {	
						novoProduto = new Produto();
						novoProduto.setSku(Integer.parseInt(sku));
						novoProduto.setDescricao(nomeProduto);
						novoProduto.setIdComercial(Integer.parseInt(idFamiliaComercial));
						produtos.add(novoProduto);
					} else {
						contErrosP++;
						erros.add("Linha "+ler.getRecordNumber() + ": "+idFamiliaComercial + ", " + nomeProduto + ", " + sku+"\n");
					}
				}
			}
		} catch (IOException e) {
			LOGGER.info(Messages.FS_ERRO_ACESSO);
			LOGGER.debug(e);
		}
		if (contErrosP == 0) {
			return produtos;
		} else {			
			throw new ErrosProduto(erros);
		}
	}

	public class ErrosProduto extends Exception {
		private List<String> errosP;

		public ErrosProduto(List<String> erroP) {
			this.errosP = erroP;
		}

		public List<String> getErro() {
			return this.errosP;
		}
	}
}