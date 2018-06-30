package br.senai.sc.edu.projetomaria.resource;

public abstract class SQL {

//	public static final String EXEMPLO;
	public static String INSERT_CANAL ;
	public static final String UPDATE_CANAL ;
	public static final String DELETE_CANAL;
	
	static {
//		EXEMPLO = ResourceManager.getSQL("exemplo.relatorio"); //$NON-NLS-1$
		INSERT_CANAL = ResourceManager.getSQL ("sql.insert.canal"); //$NON-NLS-1$
		UPDATE_CANAL = ResourceManager.getSQL ("sql.update.canal"); //$NON-NLS-1$
		DELETE_CANAL = ResourceManager.getSQL("sql.delete.canal"); //$NON-NLS-1$
	// ....
	public static final String HISTORICO_SELECT;
	
	public static final String HISTORICO_INSERT;
	
	public static final String HISTORICO_UPDATE;
	
	public static final String HISTORICO_DELETE;
	
	static {
				
		HISTORICO_SELECT = ResourceManager.getSQL("historico.select"); //$NON-NLS-1$
		HISTORICO_INSERT = ResourceManager.getSQL("historico.insert"); //$NON-NLS-1$
		HISTORICO_UPDATE = ResourceManager.getSQL("historico.update"); //$NON-NLS-1$
		HISTORICO_DELETE = ResourceManager.getSQL("historico.delete"); //$NON-NLS-1$
	}
	
	private SQL() {
	
	}
	
}



