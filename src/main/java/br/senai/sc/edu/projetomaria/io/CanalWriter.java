package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.model.Canal;

public class CanalWriter {
	
	private static final Logger LOGGER = LogManager.getLogger();

	public void generateRelatorio(Path exportPath) throws IOException {
		String filePath = exportPath.toString();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
			try(CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID_CANAL", "DESCRICAO"))){
				CanalDAO dao = new CanalDAO();
				List<Canal> canais = new ArrayList<>();
				canais = dao.getCanais();
				for (Canal canal : canais) {
					csvPrinter.printRecord(canal.getId(), canal.getDescricao());
				}
				csvPrinter.flush();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
	}

}
