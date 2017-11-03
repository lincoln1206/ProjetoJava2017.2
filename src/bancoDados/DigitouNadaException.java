package bancoDados;

public class DigitouNadaException extends Exception{
	
	private static final long serialVersionUID = -6595811286576130084L;

	public DigitouNadaException() {
		super("ERRO: Você não digitou nada e apertou em ok - tente novamente.");

	}

}
