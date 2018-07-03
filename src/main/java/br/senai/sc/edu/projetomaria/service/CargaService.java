package br.senai.sc.edu.projetomaria.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.senai.sc.edu.projetomaria.io.ProdutoReader;
import br.senai.sc.edu.projetomaria.dao.HistoricoDAO;
import br.senai.sc.edu.projetomaria.dao.ProdutoDAO;
import br.senai.sc.edu.projetomaria.io.LeitorCsv;
import br.senai.sc.edu.projetomaria.io.PhaseReader;
import br.senai.sc.edu.projetomaria.io.PhaseReader.ErrosPhase;
import br.senai.sc.edu.projetomaria.io.ProdutoReader.ErrosProduto;
import br.senai.sc.edu.projetomaria.model.Historico;
import br.senai.sc.edu.projetomaria.model.Phase;
import br.senai.sc.edu.projetomaria.model.Produto;
import br.senai.sc.edu.projetomaria.dao.CanalDAO;
import br.senai.sc.edu.projetomaria.io.CanalReader;
import br.senai.sc.edu.projetomaria.model.Canal;
import br.senai.sc.edu.projetomaria.resource.Messages;

public class CargaService {
	private static final Logger LOGGER = LogManager.getLogger();

	public void insertFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteFamilia(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertCanal(Path path) {
		CanalReader canal = new CanalReader(path);
		try {
			canal.readCanal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// throw new
		// UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void updateCanal(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deleteCanal(Path path) {
		CanalReader canal = new CanalReader(path);
		List<Canal> canais = null;
		try {
			canais = canal.readCanal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CanalDAO CanalDAO = new CanalDAO();
		CanalDAO.delete(canais);
		// throw new
		// UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void insertProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		List<Produto> produtos;
		int existeBase = 0;
		String errosReg = "";
		int linha = 1;

		try {
			produtos = reader.lerCsvProduto(path);
			ProdutoDAO dao = new ProdutoDAO();

			if (produtos.isEmpty()) {
				LOGGER.info(Messages.ERRO_VAZIO);
			} else {
				for (Produto p : produtos) {
					linha++;
					for (Produto base : dao.exportarProdutos()) {
						if (p.getSku() == base.getSku()) {
							existeBase++;
							errosReg += "Linha " + linha + ": " + p.getSku() + ", " + p.getDescricao() + ", "
									+ p.getIdComercial() + "\n";
						}
					}
				}
				if (existeBase > 0) {
					LOGGER.info(Messages.ERRO_SKU_IGUAL);
					LOGGER.info(errosReg);
					LOGGER.info(Messages.EXEC_ABORTADA);
				} else {
					dao.salvarProdutos(produtos);
				}
			}
		} catch (ErrosProduto e) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO);
			for (String p : e.getErro()) {
				LOGGER.info(p);
			}
			LOGGER.info(Messages.EXEC_ABORTADA);
		} catch (Exception i) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO2);
		}
	}

	public void updateProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		List<Produto> produtos;

		try {
			produtos = reader.lerCsvProduto(path);
			if (produtos.isEmpty()) {
				LOGGER.info(Messages.ERRO_VAZIO);
			} else {
				ProdutoDAO dao = new ProdutoDAO();
				dao.updateProduto(produtos);
			}
		} catch (ErrosProduto e) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO);
			for (String p : e.getErro()) {
				LOGGER.info(p);
			}
			LOGGER.info(Messages.EXEC_ABORTADA);
		} catch (Exception i) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO2);
		}
	}

	public void deleteProduto(Path path) {
		ProdutoReader reader = new ProdutoReader();
		List<Produto> produtos;
		int regPhase = 0;
		Set<String> set = new HashSet<>();

		try {
			produtos = reader.lerCsvProduto(path);
			if (produtos.isEmpty()) {
				LOGGER.info(Messages.ERRO_VAZIO);
			} else {
				ProdutoDAO dao = new ProdutoDAO();
				for (Produto imp : produtos) {
					for (Phase expPh : dao.exportarPhase()) {
						if (imp.getSku() == expPh.getSkuNew() || imp.getSku() == expPh.getSkuOld()) {
							regPhase++;
							set.add(imp.getSku() + ", " + imp.getDescricao() + ", " + imp.getIdComercial() + "\n");
						}
					}
				}
				if (regPhase > 0) {
					LOGGER.info(Messages.ERRO_DELETE_PHASE);
					for (String s : set) {
						LOGGER.info(s);
					}
					LOGGER.info(Messages.EXEC_ABORTADA);
				} else {
					dao.deleteProd(produtos);
				}
			}
		} catch (ErrosProduto e) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO);
			for (String p : e.getErro()) {
				LOGGER.info(p);
			}
			LOGGER.info(Messages.EXEC_ABORTADA);
		} catch (Exception i) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO2);
		}
	}

	public void insertHistorico(Path path) {
		LeitorCsv leitor = new LeitorCsv();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.persist(listaRegistros);
	}

	public void updateHistorico(Path path) {
		LeitorCsv leitor = new LeitorCsv();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.update(listaRegistros);
	}

	public void deleteHistorico(Path path) {
		LeitorCsv leitor = new LeitorCsv();
		List<Historico> listaRegistros = leitor.leitorDeArquivos(path);
		HistoricoDAO registro = new HistoricoDAO();
		registro.delete(listaRegistros);
	}

	public void insertPhase(Path path) {
		PhaseReader reader = new PhaseReader();
		List<Phase> phase;

		try {
			phase = reader.lerCsvPhase(path);
			if (phase.isEmpty()) {
				LOGGER.info(Messages.ERRO_VAZIO);
			} else {
				ProdutoDAO dao = new ProdutoDAO();
				dao.insertSkuPhase(phase);				
			}
		} catch (ErrosPhase e) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO);
			for (String p : e.getErro()) {
				LOGGER.info(p);
			}
			LOGGER.info(Messages.EXEC_ABORTADA);
		} catch (Exception i) {
			LOGGER.info(Messages.ARQUIVO_INVALIDO2);
		}
	}

	public void updatePhase(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}

	public void deletePhase(Path path) {
		throw new UnsupportedOperationException(Messages.ERRO_METODO_NAO_IMPLEMENTADO);
	}
}
