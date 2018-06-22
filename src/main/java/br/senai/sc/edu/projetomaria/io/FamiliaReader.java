package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Familia;

public class FamiliaReader {
	
	private Path path;
	private static final Logger LOGGER = LogManager.getLogger();
	
	public FamiliaReader(Path path) {
			this.path = path;

	}
	
	public List<Familia> readFamilia() throws IOException {
		BufferedReader br = Files.newBufferedReader(this.path);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("ID_FAMILIA", "CODIGO").parse(br);
		Familia familia = new Familia();
		List <Familia>list = new LinkedList<>();
		
		for (CSVRecord csvRecord: records) {
			// Acessing Values by Column Index
			
			int id_familia = Integer.parseInt(csvRecord.get("ID_FAMILIA"));
			String codigo = csvRecord.get("CODIGO");
			int pk_familia = Integer.parseInt(csvRecord.get("PK_FAMILIA"));
			
			familia.setId(id_familia);
			familia.setCodigo(codigo);
			familia.setId(pk_familia);
			list.add(familia);
		}
		LOGGER.info("Total de familias lidas: " + list.size());
		return list;
	}
}