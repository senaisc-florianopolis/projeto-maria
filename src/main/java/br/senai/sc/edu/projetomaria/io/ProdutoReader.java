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
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;

public class ProdutoReader {
	//to do : update e delete do PHASE?
	private static final Logger LOGGER = LogManager.getLogger();
	Produto novoProduto = null; 
	Phase novoPhase = null; 
	
	public ArrayList<Produto> lerCsvProduto(Path caminho){
		ArrayList<Produto> produtos = new ArrayList<>();   
	     
	      try (
	          Reader leitor = Files.newBufferedReader(caminho);
	          CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);
	          )
	      {
			  for(CSVRecord gravar : conversor){
				  if(gravar.getRecordNumber() != 1){
		              String sku = gravar.get(0);
		              String nomeProduto = gravar.get(1);
		              String idFamiliaComercial = gravar.get(2);                                              
		              
		              novoProduto = new Produto();
		              novoProduto.setSku(Integer.parseInt(sku));
		              novoProduto.setDescricao(nomeProduto);
		              novoProduto.setIdComercial(Integer.parseInt(idFamiliaComercial));
		              produtos.add(novoProduto);
				  }
	          }	  	      
	      } catch (IOException e) {
			e.printStackTrace();
			LOGGER.debug(e);
	      } 
	      return produtos;
	}
	
	public ArrayList<Phase> lerCsvPhase(Path caminho){
		ArrayList<Phase> phase = new ArrayList<>();   
	     
      try (
          Reader leitor = Files.newBufferedReader(caminho);
          CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);
          )
      {
          for(CSVRecord gravar : conversor){
        	  if(gravar.getRecordNumber() != 1){
        		  System.out.println(gravar.getRecordNumber());
	        	  String sku_new = gravar.get(0);
	              String sku_old = gravar.get(1);    

        		  novoPhase = new Phase();
                  novoPhase.setSkuNew(Integer.parseInt(sku_new));
                  novoPhase.setSkuOld(Integer.parseInt(sku_old));
	        	  
	        	  phase.add(novoPhase);
        	  }
          }	             
      } catch (IOException e) {
    	  e.printStackTrace();
    	  LOGGER.debug(e);
        } 
	      return phase;
	}
	
	public void cargaInicial(Path caminho){
		ProdutoDAO dao = new ProdutoDAO();
		
		if(lerCsvPhase(caminho).size() == 0){
			LOGGER.info("Não há produtos para inserir.");
		}else{
			dao.salvarProdutos(lerCsvProduto(caminho));
		}
	} 

	public void updateProduto(Path caminho){
		ArrayList<Produto> skuIgual = new ArrayList<>();
		ProdutoDAO dao = new ProdutoDAO();
		
		for(Produto exp: dao.exportarProdutos()){
			for(Produto imp: lerCsvProduto(caminho)){
				if(exp.getSku() == imp.getSku()){
					skuIgual.add(imp);
				}
			}
		}
		
		if(lerCsvPhase(caminho).size() == 0){
			LOGGER.info("Não há produtos para atualizar.");
		}else{
			dao.updateProduto(skuIgual);			
		}
	}
	
	public void insertPhase(Path caminho){
		ProdutoDAO dao = new ProdutoDAO();
		
		if(lerCsvPhase(caminho).size() == 0){
			LOGGER.info("Não há produtos para inserir.");
		}else{
			dao.insertSkuPhase(lerCsvPhase(caminho));
		}
	}
	
	public void deleteProduto(Path caminho){
		ProdutoDAO dao = new ProdutoDAO();
		
		if(lerCsvProduto(caminho).size() == 0){
			LOGGER.info("Não há produtos para excluir.");
		}else{
			dao.deleteProd(lerCsvProduto(caminho));
		}
	}
}