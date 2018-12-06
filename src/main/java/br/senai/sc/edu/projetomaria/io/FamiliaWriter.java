package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Config;

public class FamiliaWriter {

	private static final String SeparadorLinhas = "\n";

	private static final Logger LOGGER = LogManager.getLogger();

	public void CSVWriter(Path path, ArrayList<Familia> familias) {

		CSVFormat formatacaoCsv = CSVFormat.DEFAULT.withRecordSeparator(SeparadorLinhas)
				.withDelimiter(Config.CSV_DELIMITADOR);

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path.toString()));
				FileWriter escritorDeArquivos = new FileWriter(path.toString());
				CSVPrinter csvCompiladorDeArquivos = new CSVPrinter(escritorDeArquivos, formatacaoCsv);) {

			CSVPrinter csvPrinter = new CSVPrinter(writer, formatacaoCsv);
			{

				for (Familia familia : familias) {
					csvCompiladorDeArquivos.printRecord(familia.getCodigo(), familia.getNome());
				}
				csvPrinter.flush();
				csvPrinter.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		LOGGER.debug("O arquivo CSV criado com sucesso!");

	}
}
