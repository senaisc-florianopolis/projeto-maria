package br.senai.sc.edu.projetomaria.cli;

import java.nio.file.Files;
import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.PathConverter;

@Parameters(commandDescription = "Executa a inserção, remoção e atualização de registros")
public class CommandCarga {

	@Parameter(description = "<caminho para o arquivo de entrada>", converter = PathConverter.class)
	private Path arquivo = null;

	@Parameter(names = { "-t", "--tipo" }, description = "Tipo da carga", required = true)
	private CargaEnum tipo = null;

	@Parameter(names = { "-j", "--json" }, description = "Retorno em formato JSON")
	private boolean json = false;

	public Path getArquivo() {
		return arquivo;
	}

	public CargaEnum getTipo() {
		return tipo;
	}

	public boolean isJson() {
		return json;
	}

	public boolean isValidParameters() {
		if (this.isJson()) {
			return this.arquivo == null && this.tipo != null;
		} else {
			return this.isValidFile() && this.tipo != null;
		}
	}

	protected boolean isValidFile() {
		return this.arquivo != null && Files.isReadable(this.arquivo);
	}

}
