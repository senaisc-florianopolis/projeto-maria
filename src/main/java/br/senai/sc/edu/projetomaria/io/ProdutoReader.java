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
	
	public void ler(Path caminho){
		LOGGER.info("aqui");
		
		ArrayList<Produto> produtos = new ArrayList<>();   
	     Produto novoProduto = null;
	     int erros = 0;
	     int cont = 0;
	     
	      try (
	          Reader leitor = Files.newBufferedReader(caminho);
	          CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);
	          )
	      {
	          for(CSVRecord gravar : conversor){
	              cont++;
	              String sku = gravar.get(0);
	              String nomeProduto = gravar.get(1);
	              String idFamiliaComercial = gravar.get(2);                                              
	              
	              boolean  skuR = gravar.get(0).matches("[0-9]{1,20}");
	              boolean  nomeProdutoR = gravar.get(1).matches("[A-zÀ-ú0-9 '´]{1,255}");
	              boolean  idFamiliaComercialR = gravar.get(2).matches("[0-9]{1,20}");                                  
	              
	              if(skuR == false || nomeProdutoR == false || idFamiliaComercialR == false){
	                  erros++;
	                  System.out.println("Há um erro na linha "+cont+".");
	              }else{
	                  novoProduto = new Produto();
	                  novoProduto.setSku(Integer.parseInt(sku));
	                  novoProduto.setDescricao(nomeProduto);
	                  novoProduto.setIdComercial(Integer.parseInt(idFamiliaComercial));
	              }	              
	              produtos.add(novoProduto);              
	          }
	          if(erros == 0){
	              System.out.println("Arquivo validado e importado para a base.");
	              ProdutoDAO dao = new ProdutoDAO();               
	              dao.salvarProdutos(produtos); 
	          }
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.info("erro");
		}   
	} 
}
  

