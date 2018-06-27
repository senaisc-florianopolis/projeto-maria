package br.senai.sc.edu.projetomaria.resource;

public abstract class SQL {

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
	
	private SQL() {}
	
}
