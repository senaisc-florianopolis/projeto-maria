package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.ParseConversionEvent;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import br.senai.sc.edu.projetomaria.model.Canal;

public class CanalReader {

	private Path path;
	

	public CanalReader(Path path) {
		this.path = path;
	}

	public void readCanal() throws IOException {
		BufferedReader br = Files.newBufferedReader(this.path);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(br);
		Canal canal = new Canal();
		for (CSVRecord csvRecord : records) {
			// Accessing Values by Column Index
			
			int id_canal = Integer.parseInt(csvRecord.get("ID_CANAL"));
			String descricao = csvRecord.get("DESCRICAO");
			int pk_canal = Integer.parseInt(csvRecord.get("PK_CANAL"));
			
			canal.setId(id_canal);
			canal.setDescricao(descricao);
		}
	}
	

}
