package br.senai.sc.edu.projetomaria.io;
import java.io.FileReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Produto;

public class LeitorCsv {

	private static final String[] mapeamentoColunasArquivo = { "id_historico", "mes_ano", "quantidade", "produto_sku",
			"id_canal" };

	private static final String id_historico = "id_historico";
	private static final String mes_ano = "mes_ano";
	private static final String quantidade = "quantidade";
	private static final String produto_sku = "produto_sku";
	private static final String id_canal = "id_canal";
	private static final Logger LOGGER = LogManager.getLogger();
	//debug info warning error

	public void leitorDeArquivos(Path pathArquivo) {

		FileReader leitorDeArquivos = null;
		CSVParser parseArquivos = null;

		CSVFormat formatadorCsv = CSVFormat.DEFAULT.withHeader(mapeamentoColunasArquivo);

		try {
			List listaArquivos = new ArrayList();

			leitorDeArquivos = new FileReader(pathArquivo.toFile());
			
			parseArquivos = new CSVParser(leitorDeArquivos, formatadorCsv);
			
			List csvRecords = parseArquivos.getRecords(); 

			LOGGER.info("Arquivo lido com sucesso!");
			
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord registro = (CSVRecord) csvRecords.get(i);
				Historico historico = new Historico();
				historico.setId(Integer.parseInt(registro.get(id_historico)));
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
				historico.setPeriodo(LocalDate.parse(registro.get(mes_ano), dtf));
				historico.setQuantidade(Integer.parseInt(registro.get(quantidade)));
				Produto produto = new Produto();
				produto.setSku(Integer.parseInt(registro.get(produto_sku)));
				historico.setProduto(produto);
				Canal canal = new Canal();
				canal.setId(Integer.parseInt(registro.get(id_canal)));
				historico.setCanal(canal);
			}
			
		}catch (Exception e){
			LOGGER.info(e.getMessage());
		}
//		}finally {
//			for (int i = 1; i < leitorDeArquivos.; i++) {
//				//novo objeto
				//atribuir as propriedades
				// inserir o objeto na listagem
//			}
		
		//fechar o leitorDeArquivos
		// fechar o parseArquivos
//		
	}

}
