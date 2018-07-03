package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Familia;

public class FamiliaReader {
	
	private Path path;
	private static final Logger LOGGER = LogManager.getLogger();
	
	public FamiliaReader(Path path) {
			this.path = path;

	}
	
	public List<Familia> readFamilia() throws IOException {
		try (BufferedReader br = Files.newBufferedReader(this.path)) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("ID_FAMILIA_COMERCIAL","COD_FAMILIA_COMERCIAL", "NOME_FAMILIA_COMERCIAL").parse(br);
			
			ArrayList <Familia>list = new ArrayList<>();
			
			for (CSVRecord csvRecord: records) {
				// Acessing Values by Column Index
				int id = Integer.parseInt(csvRecord.get("ID_FAMILIA_COMERCIAL"));
				String codigo = csvRecord.get("COD_FAMILIA_COMERCIAL");
				String nome = csvRecord.get("NOME_FAMILIA_COMERCIAL");
				Familia familia = new Familia();
				familia.setId(id);
				familia.setCodigo(codigo);
				familia.setNome(nome);
				list.add(familia);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Familia> readFamiliaInterable() throws IOException {
		try(BufferedReader br = Files.newBufferedReader(this.path)){
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("COD_FAMILIA_COMERCIAL", "NOME_FAMILIA_COMERCIAL").parse(br);
			
			ArrayList<Familia> list = new ArrayList<>();

			for (CSVRecord csvRecord : records) {
				String codigo = csvRecord.get("COD_FAMILIA_COMERCIAL");
				String nome = csvRecord.get("NOME_FAMILIA_COMERCIAL");
				Familia familia = new Familia();
				familia.setCodigo(codigo);
				familia.setNome(nome);
				list.add(familia);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
