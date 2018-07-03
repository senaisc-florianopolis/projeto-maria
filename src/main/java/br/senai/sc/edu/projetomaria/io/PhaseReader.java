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
import br.senai.sc.edu.projetomaria.resource.Messages;

public class PhaseReader {
	private static final Logger LOGGER = LogManager.getLogger();
	Phase novoPhase = null;

	public List<Phase> lerCsvPhase(Path caminho) throws Exception {
		List<Phase> phase = new ArrayList<>();
		List<String> erros = new ArrayList<>();
		int contErrosP = 0;
		
		try (Reader leitor = Files.newBufferedReader(caminho);
				CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);) {
			for (CSVRecord ler : conversor) {
				if (ler.getRecordNumber() != 1) {
					String skuNew = ler.get(0);
					String skuOld = ler.get(1);

					boolean skuNewR = skuNew.matches("^[0-9]{1,20}$");
					boolean skuOldR = skuOld.matches("^[0-9]{1,20}$");

					if (skuNewR && skuOldR) {
						novoPhase = new Phase();
						novoPhase.setSkuNew(Integer.parseInt(skuNew));
						novoPhase.setSkuOld(Integer.parseInt(skuOld));
						phase.add(novoPhase);
					} else {
						contErrosP++;
						erros.add("Linha " + ler.getRecordNumber() + ": " + skuNew + ", " + skuOld + "\n");
					}
				}
			}
		} catch (IOException e) {
			LOGGER.info(Messages.FS_ERRO_ACESSO);
			LOGGER.debug(e);
		}
		if (contErrosP == 0) {
			return phase;
		} else {
			throw new ErrosPhase(erros);
		}
	}

	public class ErrosPhase extends Exception {
		private final List<String> erros;

		public ErrosPhase(List<String> erros) {
			this.erros = erros;
		}

		public List<String> getErro() {
			return this.erros;
		}
	}
}