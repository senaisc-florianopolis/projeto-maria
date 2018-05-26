package br.senai.sc.edu.projetomaria.cli;

import com.beust.jcommander.IStringConverter;

class RelatorioEnumConverter implements IStringConverter<RelatorioEnum> {

	@Override
	public RelatorioEnum convert(String value) {
		return RelatorioEnum.valueOf(value);
	}

}
