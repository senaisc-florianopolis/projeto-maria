package br.senai.sc.edu.projetomaria.controller;

import br.senai.sc.edu.projetomaria.cli.CommandCarga;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.service.CargaService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

public class CargaController {

	public ServiceResponse exec(CommandCarga command) {
		CargaService service = new CargaService();
		ServiceResponse response = null;
		switch (command.getTipo()) {
		case CANAL:
			response = service.cargaCanal(command.getArquivo());
			break;
		case FAMILIA:
			response = service.cargaFamilia(command.getArquivo());
			break;
		case PRODUTO:
			response = service.cargaProduto(command.getArquivo());
			break;
		case HISTORICO:
			response = service.cargaHistorico(command.getArquivo());
			break;
		case PHASE:
			response = service.cargaPhase(command.getArquivo());
			break;
		default:
			throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
		}
		return response;
	}

}
