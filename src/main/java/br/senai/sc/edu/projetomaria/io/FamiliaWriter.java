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

import br.senai.sc.edu.projetomaria.dao.FamiliaDAO;
import br.senai.sc.edu.projetomaria.model.Familia;

public class FamiliaWriter {
	private static final String separadorLinhas = "\n";

	public static void CSVWriter(Path path) {

		FamiliaDAO familiaDAO = new FamiliaDAO();
		
		ArrayList<Familia> listaFamilia = null;
		try {
			listaFamilia = (ArrayList<Familia>) familiaDAO.getFamilias();
		} catch (Exception e) {

		}

		FileWriter escritorDeArquivos = null;

		CSVPrinter csvCompiladorDeArquivos = null;

		CSVFormat formatacaoCsv = CSVFormat.DEFAULT.withRecordSeparator(separadorLinhas).withDelimiter(';');

		try {
			escritorDeArquivos = new FileWriter(path.toString());

			csvCompiladorDeArquivos = new CSVPrinter(escritorDeArquivos, formatacaoCsv);

			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path.toString()))) {

				CSVPrinter csvPrinter = new CSVPrinter(writer, formatacaoCsv);
				{

					for (Familia familia : listaFamilia) {
						csvCompiladorDeArquivos.printRecord(familia.getCodigo());
					}
					csvPrinter.flush();
					csvPrinter.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("O arquivo CSV criado com sucesso!");

		} catch (Exception e) {

			System.out.println("Erro no escritorDeArquivos!");
			e.printStackTrace();
		} finally {
			try {
				escritorDeArquivos.flush();
				escritorDeArquivos.close();
				csvCompiladorDeArquivos.close();
			} catch (IOException e) {
				System.out.println("Erro ao enviar/fechar o escritorDeArquivos/csvCompiladorDeArquivos!");
				e.printStackTrace();
			}
		}

	}
}
