package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.model.Canal;

public class CanalWriter {

	public CanalWriter() {
		super();
	}

	public static void generateRelatorio(Path export_path) throws IOException {
		String file_path = export_path.toString();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file_path))) {
			try(CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID_CANAL", "DESCRICAO"))){
				CanalDAO dao = new CanalDAO();
				ArrayList<Canal> canais = new ArrayList<>();
				canais = dao.getCanais();
				for (Canal canal : canais) {
					csvPrinter.printRecord(canal.getId(), canal.getDescricao());
				}
				csvPrinter.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
