package br.senai.sc.edu.projetomaria.io;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.model.Familia;
import br.senai.sc.edu.projetomaria.resource.Config;

public class FamiliaReader {
	
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String COD_FAMILIA_COMERCIAL = "COD_FAMILIA_COMERCIAL";
	private static final String NOME_FAMILIA_COMERCIAL = "NOME_FAMILIA_COMERCIAL";
	
	private static final String[] mapeamentoColunasArquivo = { COD_FAMILIA_COMERCIAL, NOME_FAMILIA_COMERCIAL };
	
	public List<Familia> leitorFamilia(Path pathArquivo) {
			
			CSVParser parseArquivos = null;
			
			CSVFormat formatadorCsv = CSVFormat.DEFAULT.withHeader(mapeamentoColunasArquivo).withDelimiter(Config.CSV_DELIMITADOR);
			
			List <Familia>list = new LinkedList<>();
			
			try (FileReader leitorFamilia = new FileReader(pathArquivo.toFile())) {
				
				parseArquivos = new CSVParser(leitorFamilia, formatadorCsv);
				
				List<CSVRecord> csvRecords = parseArquivos.getRecords();
			
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord registro = csvRecords.get(i);
				int codigo = Integer.parseInt(registro.get(COD_FAMILIA_COMERCIAL));
				String nome = registro.get(NOME_FAMILIA_COMERCIAL);
				Familia familia = new Familia();
				familia.setCodigo(codigo);
				familia.setNome(nome);
				list.add(familia);
			}
			return list;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
		return Collections.emptyList();
	}
	
}
