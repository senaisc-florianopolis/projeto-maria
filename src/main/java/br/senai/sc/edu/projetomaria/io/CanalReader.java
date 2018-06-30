package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ParseConversionEvent;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
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
		
		for (CSVRecord csvRecord : records) {
			// Accessing Values by Column Index
			
			int id_canal = Integer.parseInt(csvRecord.get("ID_CANAL"));
			String descricao = csvRecord.get("DESCRICAO");
			
			canal.setId(id_canal);
			canal.setDescricao(descricao);
			list.add(canal);
		}
		return list;
	}
	

}
