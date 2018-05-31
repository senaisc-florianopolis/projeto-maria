package br.senai.sc.edu.projetomaria.cli;

import java.nio.file.Files;
import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.PathConverter;

@Parameters(commandDescription = "Geração de relatórios")
public class CommandRelatorio {
	
	@Parameter(description = "<caminho para o arquivo de saída>", required = true, converter =  PathConverter.class)
	private Path arquivo = null;
	
	@Parameter(names = {"-t", "--tipo" }, required = true, description = "Tipo do relatório")
	private RelatorioEnum tipo = null;		

	public Path getArquivo() {
		return arquivo;
	}

	public RelatorioEnum getTipo() {
		return tipo;
	}

	public boolean isValidParameters() {
		return this.arquivo != null && Files.isReadable(this.arquivo) && this.tipo != null;
	}
	
}
