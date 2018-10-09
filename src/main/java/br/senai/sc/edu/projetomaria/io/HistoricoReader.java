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
import br.senai.sc.edu.projetomaria.resource.Messages;

public class HistoricoReader {
	
	private static final String ID_HISTORICO = "id_historico";
	private static final String MES_ANO = "mes_ano";
	private static final String QUANTIDADE = "quantidade";
	private static final String PRODUTO_SKU = "produto_sku";
	private static final String ID_CANAL = "id_canal";
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String[] mapeamentoColunasArquivo = { ID_HISTORICO, MES_ANO, QUANTIDADE, PRODUTO_SKU, ID_CANAL };

	public List<Historico> leitorDeArquivos(Path pathArquivo) {

		CSVParser parseArquivos = null;

		CSVFormat formatadorCsv = CSVFormat.DEFAULT.withHeader(mapeamentoColunasArquivo).withDelimiter(Config.CSV_DELIMITADOR);

		List<Historico> listaRegistros = new LinkedList<>();
		
		boolean wrongInserts = false;

		try (FileReader leitorDeArquivos = new FileReader(pathArquivo.toFile())) {
			
			parseArquivos = new CSVParser(leitorDeArquivos, formatadorCsv);
			
			List<CSVRecord> csvRecords = parseArquivos.getRecords();

			
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord registro = csvRecords.get(i);
				Historico historico = new Historico();
				historico.setId(this.parseInt(registro.get(ID_HISTORICO)));
				String[] mesAno = registro.get(MES_ANO).split("/");
				historico.setPeriodo(LocalDate.parse(mesAno[1] + "-" + mesAno[0] + "-01"));
				LOGGER.info(historico.getPeriodo());
				historico.setQuantidade(this.parseInt(registro.get(QUANTIDADE)));
				Produto produto = new Produto();
				produto.setSku(this.parseInt(registro.get(PRODUTO_SKU)));
				historico.setProduto(produto);
				Canal canal = new Canal();
				canal.setId(this.parseInt(registro.get(ID_CANAL)));
				historico.setCanal(canal);
				listaRegistros.add(historico);
				if (!historico.isValid()) {
					wrongInserts = true; 
					LOGGER.warn("A linha " + i + " está fora do padrão, registro ignorado.");
				}
			}
			
		}catch (Exception e){
 			LOGGER.error(e.getMessage(), e);
		}
		
		if (wrongInserts) {
			LOGGER.info(Messages.DADOS_NAO_INSERIDOS);
		}else {
			LOGGER.info(Messages.LEITURA_REALIZADA);
		}
		
		return listaRegistros;
	}
	
	protected int parseInt(String valor) {
		Integer retorno = null;
		try {
			retorno = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			LOGGER.warn(Messages.ERRO_AO_CONVERTER, e);
		}
		return retorno == null ? -1 : retorno;
	}
}
