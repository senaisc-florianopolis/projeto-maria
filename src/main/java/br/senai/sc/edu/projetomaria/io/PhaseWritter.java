package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.exception.IOLayerException;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class PhaseWritter {

	private static final Logger LOGGER = LogManager.getLogger();

	public void write(Path path, List<Phase> phases) {

		try (BufferedWriter arquivo = Files.newBufferedWriter(path);
				CSVPrinter escrever = new CSVPrinter(arquivo,
						CSVFormat.DEFAULT.withHeader("SKU_PHASE_IN", "SKU_PHASE_OUT"));) {

			for (Phase ph : phases) {
				escrever.printRecord(ph.getSkuNew(), ph.getSkuOld());
			}
		} catch (IOException e) {
			LOGGER.error(e);
			throw new IOLayerException(Messages.ERRO_ARQUIVO, e);
		}
	}
}
