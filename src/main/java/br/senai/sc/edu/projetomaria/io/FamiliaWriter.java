package br.senai.sc.edu.projetomaria.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Config;

public class FamiliaWriter {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String SEPARADOR_LINHAS = "\n";

	public void CSVWriter(Path path, List<Familia> familias) {

		CSVFormat formatacaoCsv = CSVFormat.DEFAULT.withRecordSeparator(SEPARADOR_LINHAS)
				.withDelimiter(Config.CSV_DELIMITADOR);

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path.toString()));
				FileWriter escritorDeArquivos = new FileWriter(path.toString());
				CSVPrinter csvCompiladorDeArquivos = new CSVPrinter(escritorDeArquivos, formatacaoCsv);) {

			CSVPrinter csvPrinter = new CSVPrinter(writer, formatacaoCsv);

			for (Familia familia : familias) {
				csvCompiladorDeArquivos.printRecord(familia.getCodigo(), familia.getNome());
			}
			csvPrinter.flush();
			csvPrinter.close();

		} catch (IOException e) {
			LOGGER.debug(e);
		}

		LOGGER.debug("O arquivo CSV criado com sucesso!");

	}
}
