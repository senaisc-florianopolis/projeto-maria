package br.senai.sc.edu.projetomaria.cli;

import com.beust.jcommander.Parameter;

public class CommandMain {
	
	@Parameter(names = {"-h", "-?", "--help" }, description = "Help", help = true)
	private boolean help = false;

	public boolean isHelp() {
		return help;
	}

}
