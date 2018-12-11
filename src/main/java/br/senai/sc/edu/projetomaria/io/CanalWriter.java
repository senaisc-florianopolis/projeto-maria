package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.exception.IOLayerException;
import br.senai.sc.edu.projetomaria.model.Canal;

public class CanalWriter {

	private static final Logger LOGGER = LogManager.getLogger();

	public void write(Path exportPath, List<Canal> canais) {
		String filePath = exportPath.toString();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
			try (CSVPrinter csvPrinter = new CSVPrinter(writer,
					CSVFormat.DEFAULT.withHeader("ID_CANAL", "DESCRICAO"))) {
				for (Canal canal : canais) {
					csvPrinter.printRecord(canal.getId(), canal.getDescricao());
				}
				csvPrinter.flush();
			}
		} catch (IOException e) {
			LOGGER.error(e);
			throw new IOLayerException(e);
		}
	}

}
