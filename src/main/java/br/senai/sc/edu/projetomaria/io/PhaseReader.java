package br.senai.sc.edu.projetomaria.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.exception.IOLayerException;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.resource.Config;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class PhaseReader {
	private static final Logger LOGGER = LogManager.getLogger();
	Phase novoPhase = null;
	private static final String SKU_PHASE_IN = "SKU_PHASE_IN";
	private static final String SKU_PHASE_OUT = "SKU_PHASE_OUT";


	public List<Phase> lerCsvPhase(Path caminho) {
		List<Phase> phase = new ArrayList<>();
		List<String> erros = new ArrayList<>();
		int contErrosP = 0;

		try (Reader br = Files.newBufferedReader(caminho);) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(SKU_PHASE_IN, SKU_PHASE_OUT).withDelimiter(Config.CSV_DELIMITADOR).parse(br);
			for (CSVRecord ler : records) {
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
			LOGGER.error(e);
			throw new IOLayerException(Messages.FS_ERRO_ACESSO, e);
		}
		if (contErrosP == 0) {
			return phase;
		} else {
			throw new ErrosPhase(erros);
		}
	}

	public class ErrosPhase extends IOLayerException {
		private final List<String> errosPh;

		public ErrosPhase(List<String> erroPh) {
			this.errosPh = erroPh;
		}

		public List<String> getErro() {
			return this.errosPh;
		}
	}
}