//*****************************************************************************
// 
// Projeto Maria - Projeto Integrador do SENAI/SC Florianópolis

//
// Written in 2018 by Alunos da 2a e 4a Fase de SADS
//
// To the extent possible under law, the author(s) have dedicated 
// all copyright and related and neighboring rights to this software 
// to the public domain worldwide. 
//
// This software is distributed without any warranty.
//
// You should have received a copy of the CC0 Public Domain Dedication 
// along with this software. 
//
// If not, see <http://creativecommons.org/publicdomain/zero/1.0/>. 
//
//*****************************************************************************
//aaaa
package br.senai.sc.edu.projetomaria;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import br.senai.sc.edu.projetomaria.cli.CommandCarga;
import br.senai.sc.edu.projetomaria.cli.CommandMain;
import br.senai.sc.edu.projetomaria.cli.CommandRelatorio;
import br.senai.sc.edu.projetomaria.controller.CargaController;
import br.senai.sc.edu.projetomaria.controller.RelatorioController;
import br.senai.sc.edu.projetomaria.exception.ServiceLayerException;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class ProjetoMaria {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final String COMMAND_CARGA = "carga";
	private static final String COMMAND_RELATORIO = "relatorio";

	public static void main(String[] args) {
		LOGGER.info("=== {} | {} ===", Messages.PROJETO_NOME, Messages.PROJETO_VERSAO); //$NON-NLS-1$
		CommandMain commandMain = new CommandMain();
		CommandCarga commandCarga = new CommandCarga();
		CommandRelatorio commandRelatorio = new CommandRelatorio();

		JCommander jc = JCommander.newBuilder()
				.addObject(commandMain)
				.addCommand(COMMAND_CARGA, commandCarga)
				.addCommand(COMMAND_RELATORIO, commandRelatorio)
				.build();

		try {
			jc.parse(args);
		} catch (ParameterException e) {
			LOGGER.warn(String.format(Messages.EXEC_ERRO_PARAMETROS, e.getMessage()), e);
			jc.usage();
			System.exit(1);
		}
		
		if (commandMain.isHelp()) {
			jc.usage();
			System.exit(0);
		}
		
		try {
			String command = jc.getParsedCommand() == null ? "" : jc.getParsedCommand();
			switch (command) {
			case COMMAND_CARGA:
				CargaController carga = new CargaController();
				carga.exec(commandCarga);
				break;
			case COMMAND_RELATORIO:
				RelatorioController relatorio = new RelatorioController();
				relatorio.exec(commandRelatorio);
				break;
			default:
				jc.usage();
				break;
			}	
		} catch (ServiceLayerException se) {
			LOGGER.error(String.format(Messages.EXEC_ERRO_FATAL, se.getMessage()), se);
			System.exit(1);
		} catch (RuntimeException re) {
			LOGGER.fatal(String.format(Messages.EXEC_ERRO_FATAL, re.getMessage()), re);
			LOGGER.info(Messages.EXEC_ABORTADA);
			System.exit(2);
		} catch (Exception e) {
			LOGGER.fatal(String.format(Messages.EXEC_ERRO_FATAL, e.getMessage()), e);
			LOGGER.info(Messages.EXEC_ABORTADA);
			System.exit(2);
		} 

	}

}
