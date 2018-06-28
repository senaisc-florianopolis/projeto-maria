package br.senai.sc.edu.projetomaria.controller;

import java.sql.SQLException;

import br.senai.sc.edu.projetomaria.cli.CommandCarga;
import br.senai.sc.edu.projetomaria.resource.Messages;
import br.senai.sc.edu.projetomaria.service.CargaService;

public class CargaController {

	public void exec(CommandCarga command) throws SQLException {
		switch (command.getTipo()) {
		case CANAL:
			this.execCanal(command);
			break;
		case FAMILIA:
			this.execFamilia(command);
			break;
		case PRODUTO:
			this.execProduto(command);
			break;
		case HISTORICO:
			this.execHistorico(command);
			break;
		default:
			throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
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

	protected void execFamilia(CommandCarga command) throws SQLException {
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
