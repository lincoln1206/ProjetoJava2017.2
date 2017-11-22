package exceptions;

public class TabelaInexistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1161805515811466752L;
	
	public TabelaInexistenteException() {
		super("ERRO: Tabela 'livros' inexistente!");
	}
}
