package br.senai.sc.edu.projetomaria.controller;

import br.senai.sc.edu.projetomaria.cli.CommandRelatorio;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.service.RelatorioService;

public class RelatorioController {

	public void exec(CommandRelatorio command) {
		RelatorioService service = new RelatorioService();
		switch (command.getTipo()) {
		case CANAL:
			service.exportRelatorioCanal(command.getArquivo());
			break;
		case FAMILIA:
			service.exportRelatorioFamilia(command.getArquivo());
			break;
		case PRODUTO:
			service.exportRelatorioProduto(command.getArquivo());
			break;
		case HISTORICO:
			service.exportRelatorioHistorico(command.getArquivo());
			break;
		case ESTIMATIVA:
			service.exportRelatorioEstimativa(command.getArquivo());
			break;
		case PHASE:
			service.exportRelatorioPhase(command.getArquivo());
			break;
		default:
			throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
		}
	}

}
