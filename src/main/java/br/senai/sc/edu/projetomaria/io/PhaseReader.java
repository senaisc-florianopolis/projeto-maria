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
	int contErrosP = 0;

	public List<Phase> lerCsvPhase(Path caminho) throws Exception {
		List<Phase> phase = new ArrayList<>();

		try (Reader leitor = Files.newBufferedReader(caminho);
				CSVParser conversor = new CSVParser(leitor, CSVFormat.DEFAULT);) {
			for (CSVRecord ler : conversor) {
				if (ler.getRecordNumber() != 1) {
					String skuNew = ler.get(0);
					String skuOld = ler.get(1);

					boolean skuNewR = skuNew.matches("[0,9],{1,20}");
					boolean skuOldR = skuOld.matches("[0,9],{1,20}");

					if (skuNewR || skuOldR) {
						contErrosP++;
					} else {
						novoPhase = new Phase();
						novoPhase.setSkuNew(Integer.parseInt(skuNew));
						novoPhase.setSkuOld(Integer.parseInt(skuOld));
						phase.add(novoPhase);
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
			throw new Exception();
		}
	}
}
