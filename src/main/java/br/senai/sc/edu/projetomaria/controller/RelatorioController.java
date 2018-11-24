package br.senai.sc.edu.projetomaria.controller;

import br.senai.sc.edu.projetomaria.cli.CommandRelatorio;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.service.RelatorioService;
import br.senai.sc.edu.projetomaria.service.ServiceResponse;

public class RelatorioController {

	public ServiceResponse exec(CommandRelatorio command) {
		ServiceResponse response = null;
		switch (command.getTipo()) {
		case CANAL:
			response = this.exportarCanal(command);
			break;
		case FAMILIA:
			response = this.exportarFamilia(command);
			break;
		case PRODUTO:
			response = this.exportarProduto(command);
			break;
		case HISTORICO:
			response = this.exportarHistorico(command);
			break;
		case ESTIMATIVA:
			response = this.exportarEstimativa(command);
			break;
		case PHASE:
			response = this.exportarPhase(command);
			break;
		default:
			throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
		}
		return response;
	}

	public ServiceResponse exportarCanal(CommandRelatorio command) {
		RelatorioService service = new RelatorioService();
		return command.isJson() ? service.exportarCanal() : service.exportarCanal(command.getArquivo());
	}

	public ServiceResponse exportarFamilia(CommandRelatorio command) {
		RelatorioService service = new RelatorioService();
		return command.isJson() ? service.exportarFamilia() : service.exportarFamilia(command.getArquivo());
	}

	public ServiceResponse exportarProduto(CommandRelatorio command) {
		RelatorioService service = new RelatorioService();
		return command.isJson() ? service.exportarProduto() : service.exportarProduto(command.getArquivo());
	}

	public ServiceResponse exportarHistorico(CommandRelatorio command) {
		RelatorioService service = new RelatorioService();
		return command.isJson() ? service.exportarHistorico() : service.exportarHistorico(command.getArquivo());
	}

	public ServiceResponse exportarEstimativa(CommandRelatorio command) {
		RelatorioService service = new RelatorioService();
		int periodoAnterior = command.getPeriodoAnterior();
		return command.isJson() ? service.exportarEstimativa(periodoAnterior)
				: service.exportarEstimativa(command.getArquivo(), periodoAnterior);
	}

	public ServiceResponse exportarPhase(CommandRelatorio command) {
		RelatorioService service = new RelatorioService();
		return command.isJson() ? service.exportarPhase() : service.exportarPhase(command.getArquivo());
	}

}
