package br.senai.sc.edu.projetomaria.cli;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.beust.jcommander.IStringConverter;

class PathConverter implements IStringConverter<Path> {

	@Override
	public Path convert(String value) {
		return Paths.get(value);
	}

}
