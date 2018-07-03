package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Resultado;

public class EstimativaWritter {
	public void escrever(Path path, int periodo) {

		try (BufferedWriter arquivo = Files.newBufferedWriter(path);
				CSVPrinter escrever = new CSVPrinter(arquivo,
						CSVFormat.DEFAULT.withHeader("SKU", "PERIODO TOTAL",
								"RESULTADO 1", "RESULTADO 2", "RESULTADO 3",
								"RESULTADO 4", "ERRO QUADRATICO",
								"PERIODO UTILIZADO"));) {
			Estimativa estimativa = new Estimativa();
			String teste = "";
			int cont = 0;
			
			/*for(Resultado i: estimativa.Calculo(periodo)){
				cont++;
				teste += i.getPeriodo_utilizado()[cont]+",";
				System.out.println(cont);
			}*/
			
			
			for (Resultado linha : estimativa.Calculo(periodo)) {
				escrever.printRecord(linha.getSKU(), linha.getPeriodo_total(),
						linha.getResultado_media(),
						linha.getResultado_media2(),
						linha.getResultado_media3(),
						linha.getResultado_media4(),
						linha.getErro_quadratico_medio()
						);
			}
		} catch (IOException ex) {
			Logger.getLogger(EstimativaWritter.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}
}
