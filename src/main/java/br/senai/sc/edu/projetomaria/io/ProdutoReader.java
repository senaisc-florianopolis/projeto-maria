package br.senai.sc.edu.projetomaria.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Produto;


public class ProdutoReader {
	private static final Logger LOGGER = LogManager.getLogger();
	//bdd1
	
	public void cargaInicial(Path caminho){
		ArrayList<Produto> produtos = new ArrayList<>();   
	     Produto novoProduto = null; 
	      try (
	          Reader leitor = Files.newBufferedReader(caminho);
	          CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);
	          )
	      {
	          for(CSVRecord gravar : conversor){
	              String sku = gravar.get(0);
	              String nomeProduto = gravar.get(1);
	              String idFamiliaComercial = gravar.get(2);                                              
	              
                  novoProduto = new Produto();
                  novoProduto.setSku(Integer.parseInt(sku));
                  novoProduto.setDescricao(nomeProduto);
                  novoProduto.setIdComercial(Integer.parseInt(idFamiliaComercial));
                  produtos.add(novoProduto);
	          }	              
              ProdutoDAO dao = new ProdutoDAO();               
              dao.salvarProdutos(produtos); 
	      } catch (IOException e) {
			e.printStackTrace();
		}   
	} 
}
  

