//*****************************************************************************
// 
// Projeto Maria - Projeto Integrador do SENAI/SC Florianópolis

//
// Written in 2018 by Alunos do curso de SADS
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
package br.senai.sc.edu.projetomaria;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.senai.sc.edu.projetomaria.cli.CommandCarga;
import br.senai.sc.edu.projetomaria.cli.CommandMain;
import br.senai.sc.edu.projetomaria.cli.CommandRelatorio;
import br.senai.sc.edu.projetomaria.controller.CargaController;
import br.senai.sc.edu.projetomaria.controller.RelatorioController;
import br.senai.sc.edu.projetomaria.exception.ServiceLayerException;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

public class ProjetoMaria {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final String COMMAND_CARGA = "carga";
	private static final String COMMAND_RELATORIO = "relatorio";

	public static void main(String[] args) {
		LOGGER.info("=== {} | {} ===", Messages.PROJETO_NOME, Messages.PROJETO_VERSAO); //$NON-NLS-1$
		CommandMain commandMain = new CommandMain();
		CommandCarga commandCarga = new CommandCarga();
		CommandRelatorio commandRelatorio = new CommandRelatorio();

		JCommander jc = JCommander.newBuilder().addObject(commandMain).addCommand(COMMAND_CARGA, commandCarga)
				.addCommand(COMMAND_RELATORIO, commandRelatorio).build();

		// Valida os parâmetros informados
		try {
			jc.parse(args);
		} catch (ParameterException e) {
			LOGGER.warn(String.format(Messages.EXEC_ERRO_PARAMETROS, e.getMessage()), e);
			jc.usage();
			System.exit(1);
		}

		// Apresenta a ajuda se solicitada
		if (commandMain.isHelp()) {
			jc.usage();
			System.exit(0);
		}

		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		try {
			ServiceResponse response = null;
			String command = jc.getParsedCommand() == null ? "" : jc.getParsedCommand();
			switch (command) {
			case COMMAND_CARGA:
				CargaController carga = new CargaController();
				response = carga.exec(commandCarga);
				break;
			case COMMAND_RELATORIO:
				RelatorioController relatorio = new RelatorioController();
				response = relatorio.exec(commandRelatorio);
				break;
			}
			String json = gson.toJson(response);
			System.out.print(json);
		} catch (ServiceLayerException se) {
			LOGGER.error(String.format(Messages.EXEC_ERRO_FATAL, se.getMessage()), se);
			Map<String, String> erro = new LinkedHashMap<>();
			erro.put("Status", "ERROR");
			erro.put("Erro", se.getMessage());
			String json = gson.toJson(erro);
			System.out.print(json);
			System.exit(1);
		} catch (RuntimeException re) {
			LOGGER.fatal(String.format(Messages.EXEC_ERRO_FATAL, re.getMessage()), re);
			LOGGER.info(Messages.EXEC_ABORTADA);
			Map<String, String> erro = new LinkedHashMap<>();
			erro.put("Status", "ERROR");
			erro.put("Erro", re.getMessage());
			String json = gson.toJson(erro);
			System.out.print(json);
			System.exit(2);
		}

	}

}
