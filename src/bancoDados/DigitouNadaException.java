package bancoDados;

public class DigitouNadaException extends Exception{
	
	private static final long serialVersionUID = -6595811286576130084L;

	public DigitouNadaException() {
		super("ERRO: Voc� n�o digitou nada e apertou em ok - tente novamente.");

	}

}
