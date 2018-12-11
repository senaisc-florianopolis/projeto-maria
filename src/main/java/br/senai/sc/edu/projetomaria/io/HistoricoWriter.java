package br.senai.sc.edu.projetomaria.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.exception.IOLayerException;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.resource.Config;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class HistoricoWriter {

	private static final String SEPARADORLINHAS = "\n";
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Object[] colunasArquivo = { "id_historico", "mes_ano", "quantidade", "produto_sku",
			"id_canal" };

	public void write(Path nomeArquivo, List<Historico> registro) {

		CSVFormat formatacaoCsv = CSVFormat.DEFAULT.withRecordSeparator(SEPARADORLINHAS)
				.withDelimiter(Config.CSV_DELIMITADOR);

		try (FileWriter escritorDeArquivos = new FileWriter(nomeArquivo.toFile());
				CSVPrinter csvCompiladorDeArquivos = new CSVPrinter(escritorDeArquivos, formatacaoCsv);) {

			csvCompiladorDeArquivos.printRecord(colunasArquivo);

			for (Historico historico : registro) {
				csvCompiladorDeArquivos.printRecord(historico.getPeriodo(), historico.getQuantidade(),
						historico.getProduto().getSku(), historico.getCanal());
			}

			LOGGER.info(Messages.ARQUIVO_CRIADO_COM_SUCESSO);

		} catch (IOException e) {
			LOGGER.error(e);
			throw new IOLayerException(Messages.ERRO_ARQUIVO, e);
		}
	}
}
