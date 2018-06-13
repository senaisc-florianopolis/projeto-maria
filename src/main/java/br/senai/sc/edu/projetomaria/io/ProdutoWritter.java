package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Produto;

public class ProdutoWritter {
	private void escrever(){
		
		String caminho  = "";
	    try(             
	        BufferedWriter arquivo = Files.newBufferedWriter(Paths.get(caminho));                        
	        CSVPrinter escrever = new CSVPrinter(arquivo, CSVFormat.DEFAULT.withHeader("SKU", "NOME_PRODUTO", "ID_FAMILIA_COMERCIAL"));
	        )
	    {
	    	ProdutoDAO dao = new ProdutoDAO();
	        for(Produto p : dao.exportarProdutos()){
	            escrever.printRecord(p.getSku(), p.getDescricao(), p.getIdComercial());
	        }      
	        System.out.println("Arquivo exportado!");
	    }catch (IOException ex) {
	        Logger.getLogger(ProdutoWritter.class.getName()).log(Level.SEVERE, null, ex);
	    }	
	}
}
