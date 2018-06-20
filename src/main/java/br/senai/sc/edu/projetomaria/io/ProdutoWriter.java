package br.senai.sc.edu.projetomaria.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ProdutoWriter {

	public static void CSVWriter(String path, String filename) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path + filename));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.withHeader("ID", "Name", "Country", "City"));) {

			csvPrinter.printRecord("1", "Andrey Freitas ", "Brasil", "Florianópolis");
			csvPrinter.printRecord("2", "Murilo", "Brasil", "Lages");

			csvPrinter.printRecord(Arrays.asList("4", "Fulano", "Argentina", "Buenos Aires"));

			// for (int i = 0; i < 10; i++) {
			// csvPrinter.printRecord(i, "Guilherme", "Brasil", "Florianópolis");
			// }

			csvPrinter.flush();
		}
	}
}