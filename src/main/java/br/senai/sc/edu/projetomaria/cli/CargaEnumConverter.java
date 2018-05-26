package br.senai.sc.edu.projetomaria.cli;

import com.beust.jcommander.IStringConverter;

class CargaEnumConverter implements IStringConverter<CargaEnum> {

	@Override
	public CargaEnum convert(String value) {
		return CargaEnum.valueOf(value);
	}

}
