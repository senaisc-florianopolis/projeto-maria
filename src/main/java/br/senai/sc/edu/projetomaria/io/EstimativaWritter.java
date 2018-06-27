package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.senai.sc.edu.projetomaria.model.Historico;

public class EstimativaWritter {
	//to do : verificar com o professo se havera entrada de meses utilizados para estimativa
		public void escrever(Path path){	
		    try(             
		        BufferedWriter arquivo = Files.newBufferedWriter(path);                        
		        CSVPrinter escrever = new CSVPrinter(arquivo, CSVFormat.DEFAULT.withHeader("SKU", "NOME_PRODUTO", "ID_FAMILIA_COMERCIAL"));
		        )
		    {
		    	Historico historico = new Historico();
		    }catch (IOException ex) {
		        Logger.getLogger(EstimativaWritter.class.getName()).log(Level.SEVERE, null, ex);
		    }	
		}
}
