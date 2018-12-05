package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.senai.sc.edu.projetomaria.dao.PhaseDAO;
import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class PhaseWritter {

	private static final Logger LOGGER = LogManager.getLogger();

	public void gerarArquivoPhase(Path path) {

		try (BufferedWriter arquivo = Files.newBufferedWriter(path);
				CSVPrinter escrever = new CSVPrinter(arquivo,
						CSVFormat.DEFAULT.withHeader("SKU_PHASE_IN",
								"SKU_PHASE_OUT"));) {

			PhaseDAO dao = new PhaseDAO();
			for (Phase ph : dao.exportarPhase()) {
				escrever.printRecord(ph.getSkuNew(), ph.getSkuOld());
			}
			LOGGER.info(Messages.ARQUIVO_GERADO);
		} catch (IOException ex) {
			LOGGER.info(Messages.ERRO_ARQUIVO);
			LOGGER.debug(ex);
		}
	}
}
