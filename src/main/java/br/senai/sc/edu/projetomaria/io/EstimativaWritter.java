package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.senai.sc.edu.projetomaria.exception.IOLayerException;
import br.senai.sc.edu.projetomaria.model.Resultado;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class EstimativaWritter {
	private static final Logger LOGGER = LogManager.getLogger();

	protected String[] getHeader(int periodo) {
		String[] header = new String[7 + periodo];
		header[0] = "SKU";
		header[1] = "PERIODO ANALISADO";
		header[2] = "RESULTADO 1";
		header[3] = "RESULTADO 2";
		header[4] = "RESULTADO 3";
		header[5] = "RESULTADO 4";		
		header[6] = "ERRO QUADRATICO";
		for (int i = 7; i < (periodo + 7); i++) {
			header[i] = "P" + i;
		}
		return header;
	}

	protected Object[] getEscrever(Resultado linha) {
		int aux = 7;
		int tamanho = linha.getPeriodoUtilizado().length + aux;
		Object[] retorno = new Object[tamanho];
		retorno[0] = linha.getSKU();
		retorno[1] =  linha.getPeriodoTotal();
		retorno[2] = linha.getResultadoMedia();
		retorno[3] = linha.getResultadoMedia2();
		retorno[4] = linha.getResultadoMedia3();
		retorno[5] = linha.getResultadoMedia4();		
		retorno[6] = linha.getErroQuadraticoMedio();
		for (Integer periodo : linha.getPeriodoUtilizado()) {			
			retorno[aux] = periodo;
			aux++;
		}
		return retorno;
	}

	public void escrever(Path path, int periodo) {
		String[] header = this.getHeader(periodo);
		try (BufferedWriter arquivo = Files.newBufferedWriter(path);
				CSVPrinter escrever = new CSVPrinter(arquivo,
						CSVFormat.DEFAULT.withHeader(header));) {
			Estimativa estimativa = new Estimativa();

			for (Resultado linha : estimativa.calculo(periodo)) {
				
				escrever.printRecord(getEscrever(linha));
			}
			LOGGER.info(Messages.ARQUIVO_GERADO);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new IOLayerException(Messages.ERRO_ARQUIVO, e);
		}
	}
}
