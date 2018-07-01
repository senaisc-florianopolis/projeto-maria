package br.senai.sc.edu.projetomaria.cli;

import java.nio.file.Files;
import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.PathConverter;

@Parameters(commandDescription = "Executa a inserção, remoção e atualização de registros")
public class CommandCarga {

	@Parameter(description = "<caminho para o arquivo de entrada>", required = true, converter = PathConverter.class)
	private Path arquivo = null;

	@Parameter(names = { "-t", "--tipo" }, description = "Tipo da carga", required = true)
	private CargaEnum tipo = null;

	@Parameter(names = { "-i", "--insert" }, description = "Inserção de registros")
	private boolean insert = false;

	@Parameter(names = { "-d", "--delete" }, description = "Remoção de registros")
	private boolean delete = false;

	@Parameter(names = { "-u", "--update" }, description = "Atualização de registros")
	private boolean update = false;

	public Path getArquivo() {
		return arquivo;
	}

	public CargaEnum getTipo() {
		return tipo;
	}

	public boolean isInsert() {
		return insert;
	}

	public boolean isDelete() {
		return delete;
	}

	public boolean isUpdate() {
		return update;
	}

	public boolean isValidParameters() {
		return this.isValidFile() && this.isValidOperation() && this.tipo != null;
	}

	protected boolean isValidOperation() {
		int nrOperations = 0;
		if (this.insert) {
			nrOperations++;
		}
		if (this.update) {
			nrOperations++;
		}
		if (this.delete) {
			nrOperations++;
		}
		return nrOperations == 1;
	}

	protected boolean isValidFile() {
		return this.arquivo != null && Files.isReadable(this.arquivo);
	}

}
