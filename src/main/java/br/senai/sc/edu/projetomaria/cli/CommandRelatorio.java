package br.senai.sc.edu.projetomaria.cli;

import java.nio.file.Files;
import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.PathConverter;

@Parameters(commandDescription = "Geração de relatórios")
public class CommandRelatorio {

	@Parameter(description = "<caminho para o arquivo de saída>", converter = PathConverter.class)
	private Path arquivo = null;

	@Parameter(names = { "-t", "--tipo" }, required = true, description = "Tipo do relatório")
	private RelatorioEnum tipo = null;

	@Parameter(names = { "-pa",
			"--periodoanterior" }, description = "Periodo anterior (obrigatório para o tipo ESTIMATIVA)")
	private int periodoAnterior = 0;

	@Parameter(names = { "-j", "--json" }, description = "Retorno em formato JSON")
	private boolean json = false;

	public Path getArquivo() {
		return arquivo;
	}

	public RelatorioEnum getTipo() {
		return tipo;
	}

	public int getPeriodoAnterior() {
		return periodoAnterior;
	}

	public boolean isJson() {
		return json;
	}

	public boolean isValidParameters() {
		if (this.isJson() && this.arquivo != null) {
			return false;
		}
		if (this.tipo == RelatorioEnum.ESTIMATIVA && this.periodoAnterior == 0) {
			return false;
		} else {
			return this.arquivo != null && Files.isReadable(this.arquivo) && this.tipo != null;
		}
	}

}
