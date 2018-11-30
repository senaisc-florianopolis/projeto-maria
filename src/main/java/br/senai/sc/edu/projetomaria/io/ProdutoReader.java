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

import br.senai.sc.edu.projetomaria.exception.DAOLayerException;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Config;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class ProdutoReader {
	
	private static final String COD_FAMILIA_COMERCIAL = "cod_familia_comercial";
	private static final String NOME_PRODUTO = "nome_produto";
	private static final String SKU = "sku";
	private static final String[] mapeamentoColunasArquivo = { COD_FAMILIA_COMERCIAL, NOME_PRODUTO, SKU};
	
	private static final Logger LOGGER = LogManager.getLogger();

	Produto novoProduto = null;

	public List<Produto> lerCsvProduto(Path caminho) {
		int contErrosP = 0;

		List<Produto> produtos = new ArrayList<>();
		List<String> erros = new ArrayList<>();
		try (Reader br = Files.newBufferedReader(caminho);
				) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(mapeamentoColunasArquivo).withDelimiter(Config.CSV_DELIMITADOR).withFirstRecordAsHeader().parse(br);
			for (CSVRecord ler : records) {
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
		} catch (IOException e) {
			LOGGER.info(Messages.FS_ERRO_ACESSO);
			LOGGER.debug(e);
			throw new DAOLayerException(e);
		}
		if (contErrosP != 0) {		
			// FIXME: ajustar mensagem de erro
			throw new DAOLayerException();
		}
		LOGGER.debug("QTD: " + produtos.size());
		return produtos;
	}


}