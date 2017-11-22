package exceptions;

public class SemDadosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3222752220801601347L;
	
	public SemDadosException() {
		super("ERRO: Tabela 'livros' sem dados!");
	}

}
