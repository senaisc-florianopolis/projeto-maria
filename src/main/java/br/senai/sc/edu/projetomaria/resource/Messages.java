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
	public static final String EXEC_ERRO_RECURSO_OBRIGATORIO = ResourceManager.getMessage("exec.erro.recurso.obrigatorio"); //$NON-NLS-1$
	
	// Outras Mensagens
	public static final String ERRO_METODO_NAO_IMPLEMENTADO = ResourceManager.getMessage("erro.metodo.nao.implementado"); //$NON-NLS-1$
  public static final String ERRO_EXECUCAO_DELETE = ResourceManager.getMessage("erro.falta.informacao"); //$NON-NLS-1$
  public static final String SUCESSO_RELATORIO_CANAL = ResourceManager.getMessage("sucesso.relatorio.canal"); //$NON-NLS-1$
  
  	// Produtos
  	public static final String ERRO_VAZIO = ResourceManager.getMessage("erro.vazio"); //$NON-NLS-1$
  	public static final String SUCCESS_PRODUTO = ResourceManager.getMessage("success.produto"); //$NON-NLS-1$
  	public static final String ARQUIVO_GERADO = ResourceManager.getMessage("arquivo.gerado"); //$NON-NLS-1$
  	public static final String ERRO_ARQUIVO = ResourceManager.getMessage("erro.arquivo"); //$NON-NLS-1$
  	public static final String ARQUIVO_INVALIDO = ResourceManager.getMessage("arquivo.invalido"); //$NON-NLS-1$
  	public static final String ARQUIVO_INVALIDO2 = ResourceManager.getMessage("arquivo.invalido2"); //$NON-NLS-1$
  	public static final String ERRO_DELETE_PHASE = ResourceManager.getMessage("erro.delete.phase"); //$NON-NLS-1$
  	public static final String ERRO_SKU_IGUAL = ResourceManager.getMessage("erro.sku.igual"); //$NON-NLS-1$
  	public static final String ERRO_PRODUTO_IGUAL = ResourceManager.getMessage("erro.produto.igual"); //$NON-NLS-1$
  	
	private Messages() {}

}
