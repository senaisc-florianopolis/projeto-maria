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

import br.senai.sc.edu.projetomaria.model.Canal;

public class CanalReader {

	private Path path;
	private static final Logger LOGGER = LogManager.getLogger();

	public CanalReader(Path path) {
		this.path = path;
	}

	public List<Canal> readCanal() throws IOException {
		BufferedReader br = Files.newBufferedReader(this.path);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("ID_CANAL", "DESCRICAO").parse(br);
		Canal canal = new Canal();
		ArrayList<Canal> list = new ArrayList<>();
		
		return null; 
	}
}
