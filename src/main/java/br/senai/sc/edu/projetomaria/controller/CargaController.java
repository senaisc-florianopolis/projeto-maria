package br.senai.sc.edu.projetomaria.controller;

import br.senai.sc.edu.projetomaria.cli.CommandCarga;
import br.senai.sc.edu.projetomaria.service.CargaService;

public class CargaController {

	public void exec(CommandCarga command) {
		switch (command.getTipo()) {
		case canal:
			this.execCanal(command);
			break;
		case familia:
			this.execFamilia(command);
			break;
		case produto:
			this.execProduto(command);
			break;
		case historico:
			this.execHistorico(command);
			break;
		default:
			throw new UnsupportedOperationException("Opção não implementada."); //$NON-NLS-1$
		}
	}

	protected void execCanal(CommandCarga command) {
		CargaService service = new CargaService();
		if (command.isInsert()) {
			service.insertCanal(command.getArquivo());
		} else if (command.isDelete()) {
			service.deleteCanal(command.getArquivo());
		} else if (command.isUpdate()) {
			service.updateCanal(command.getArquivo());
		}
	}

	protected void execFamilia(CommandCarga command) {
		CargaService service = new CargaService();
		if (command.isInsert()) {
			service.insertFamilia(command.getArquivo());
		} else if (command.isDelete()) {
			service.deleteFamilia(command.getArquivo());
		} else if (command.isUpdate()) {
			service.updateFamilia(command.getArquivo());
		}
	}

	protected void execProduto(CommandCarga command) {
		CargaService service = new CargaService();
		if (command.isInsert()) {
			service.insertProduto(command.getArquivo());
		} else if (command.isDelete()) {
			service.deleteProduto(command.getArquivo());
		} else if (command.isUpdate()) {
			service.updateProduto(command.getArquivo());
		}
	}

	protected void execHistorico(CommandCarga cm) {
		CargaService service = new CargaService();
		if (cm.isInsert()) {
			service.insertHistorico(cm.getArquivo());
		} else if (cm.isDelete()) {
			service.deleteHistorico(cm.getArquivo());
		} else if (cm.isUpdate()) {
			service.updateHistorico(cm.getArquivo());
		}
	}

}
