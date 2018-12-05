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

import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.model.Produto;

public class ProdutoWriter {
	private static final String separadorLinhas = "\n";

	// public static void CSVWriter(String path, ArrayList<Produto> listaProdutos) {
	public static void CSVWriter(Path path) {

		ProdutoDAO produtoDAO = new ProdutoDAO();

		ArrayList<Produto> listaProdutos = null;
		try {
			listaProdutos = (ArrayList<Produto>) produtoDAO.listarTodos();
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

					for (Produto produto : listaProdutos) {
						csvCompiladorDeArquivos.printRecord(produto.getSku(), produto.getDescricao());
						// csvPrinter.printRecord(produto.getSku(), produto.getDescricao());
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
				// SUBSTITUIR POSTERIORMENTE POR LOGGER? - PERGUNTAR AO LUCIANO
				System.out.println("Erro ao enviar/fechar o escritorDeArquivos/csvCompiladorDeArquivos!");
				e.printStackTrace();
			}
		}
	}

}
