package br.senai.sc.edu.projetomaria.io;
import java.io.FileReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.resource.Config;

public class HistoricoReader {

	private static final String[] mapeamentoColunasArquivo = { "id_historico", "mes_ano", "quantidade", "produto_sku",
			"id_canal" };

	private static final String id_historico = "id_historico";
	private static final String mes_ano = "mes_ano";
	private static final String quantidade = "quantidade";
	private static final String produto_sku = "produto_sku";
	private static final String id_canal = "id_canal";
	private static final Logger LOGGER = LogManager.getLogger();
	//debug info warning error

	public List<Historico> leitorDeArquivos(Path pathArquivo) {

		CSVParser parseArquivos = null;

		CSVFormat formatadorCsv = CSVFormat.DEFAULT.withHeader(mapeamentoColunasArquivo).withDelimiter(Config.CSV_DELIMITADOR);
		LOGGER.debug("Delimitador CSV: " + Config.CSV_DELIMITADOR);

		List<Historico> listaRegistros = new LinkedList<>();
		
		boolean listIsRight = true;

		try (FileReader leitorDeArquivos = new FileReader(pathArquivo.toFile())) {
			
			parseArquivos = new CSVParser(leitorDeArquivos, formatadorCsv);
			
			List<CSVRecord> csvRecords = parseArquivos.getRecords();

			LOGGER.info("Leitura realizada, iniciando extração de dados");
			
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord registro = csvRecords.get(i);
				Historico historico = new Historico();
				historico.setId(Integer.parseInt(registro.get(id_historico)));
				String[] mesAno = registro.get(mes_ano).split("/");
//				String dateFormat = String.format("%1$04d-%2$02d-01", mesAno[1], mesAno[0]);
//				historico.setPeriodo(LocalDate.parse(dateFormat));
				historico.setPeriodo(LocalDate.parse(mesAno[1] + "-" + mesAno[0] + "-01"));
				LOGGER.info(historico.getPeriodo());
				historico.setQuantidade(Integer.parseInt(registro.get(quantidade)));
				Produto produto = new Produto();
				produto.setSku(Integer.parseInt(registro.get(produto_sku)));
				historico.setProduto(produto);
				Canal canal = new Canal();
				canal.setId(Integer.parseInt(registro.get(id_canal)));
				historico.setCanal(canal);
				listaRegistros.add(historico);
				if (!historico.isValid()) {
					listIsRight = false; 
					LOGGER.warn("O produto " + historico.getProduto().getSku() +
							", relativo ao período" + historico.getPeriodo() + ", não foi inserido");
				};
			}
		}catch (Exception e){
 			LOGGER.error(e.getMessage(), e);
		}
		
		if (listIsRight == false) {
			System.out.println("Os dados não foram inseridos em sua totalidade. Acesse o log para detalhes");
		}
		
		return listaRegistros;
	}
}
