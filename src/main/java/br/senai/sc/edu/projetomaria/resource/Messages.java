package br.senai.sc.edu.projetomaria.resource;

public abstract class Messages {

	// Dados do Projeto
	public static final String PROJETO_NOME = ResourceManager.getMessage("projeto.nome"); //$NON-NLS-1$
	public static final String PROJETO_VERSAO = ResourceManager.getMessage("projeto.versao"); //$NON-NLS-1$

	// Banco de Dados
	public static final String BD_ERRO_CONEXAO = ResourceManager.getMessage("bd.erro.conexao"); //$NON-NLS-1$

	// FileSystem
	public static final String FS_ERRO_ACESSO = ResourceManager.getMessage("fs.erro.acesso"); //$NON-NLS-1$

	// Controle de Execução
	public static final String EXEC_ERRO_PARAMETROS = ResourceManager.getMessage("exec.erro.parametros"); //$NON-NLS-1$
	public static final String EXEC_ERRO_FATAL = ResourceManager.getMessage("exec.erro.fatal"); //$NON-NLS-1$
	public static final String EXEC_ABORTADA = ResourceManager.getMessage("exec.abortada"); //$NON-NLS-1$

	private Messages() {
	};

}
