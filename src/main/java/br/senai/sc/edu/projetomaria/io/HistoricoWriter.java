package br.senai.sc.edu.projetomaria.io;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class HistoricoWriter {

	private static final String separadorLinhas = "\n";
	private static final Logger LOGGER = LogManager.getLogger();
	private  static final Object[] colunasArquivo = { "id_historico", "mes_ano", "quantidade", "produto_sku", "id_canal" };

	public void writeCsvFile(Path nomeArquivo, List <Historico> registro) {
		
		FileWriter escritorDeArquivos = null;

		CSVPrinter csvCompiladorDeArquivos = null;

		CSVFormat formatacaoCsv = CSVFormat.DEFAULT.withRecordSeparator(separadorLinhas).withDelimiter(';');

		try {
			escritorDeArquivos = new FileWriter(nomeArquivo.toFile());

			csvCompiladorDeArquivos = new CSVPrinter(escritorDeArquivos, formatacaoCsv);

			csvCompiladorDeArquivos.printRecord(colunasArquivo);
				
			for (Historico historico : registro) {
//				historico.getId();
//				historico.getPeriodo();
//				historico.getQuantidade();
//				historico.getProduto().getSku();
//				historico.getCanal();
				csvCompiladorDeArquivos.printRecord(historico.getId(), historico.getPeriodo(), historico.getQuantidade(), historico.getProduto().getSku(), historico.getCanal());
			}
			
			
			LOGGER.info(Messages.ARQUIVO_CRIADO_COM_SUCESSO);

		} catch (Exception e) {
			
			LOGGER.info(Messages.ERRO_ESCRITOR_DE_ARQUIVO);
			e.printStackTrace();
		} finally {
			try {
				escritorDeArquivos.flush();
				escritorDeArquivos.close();
				csvCompiladorDeArquivos.close();
			} catch (IOException e) {		
				LOGGER.info(Messages.ERRO_AO_ENVIAR);
				e.printStackTrace();
			}
		}
	}
}
