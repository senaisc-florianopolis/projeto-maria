package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.exception.IOLayerException;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Config;

public class CanalReader {

	private Path path;
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String ID_CANAL = "ID_CANAL";
	private static final String DESCRICAO = "DESCRICAO";
	

	public CanalReader(Path path) {
		this.path = path;
	}

	public List<Canal> readCanal() {
		
		ArrayList<Canal> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(this.path)) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader(ID_CANAL, DESCRICAO)
					.withDelimiter(Config.CSV_DELIMITADOR).withFirstRecordAsHeader().parse(br);
			for (CSVRecord csvRecord : records) {
				String idCanal = csvRecord.get(ID_CANAL);
				String descricao = csvRecord.get(DESCRICAO);
				LOGGER.debug("TESTE '" + idCanal + "' ");
				Canal canal = new Canal();
				canal.setId(Integer.parseInt(idCanal));
				canal.setDescricao(descricao);
				list.add(canal);
			}
		} catch (IOException e) {
			LOGGER.error(e);
			throw new IOLayerException("", e);
		}

		return list;
	}

}
