package br.com.empresa.validator;

/**
 * Classe business para tratar exce��es de neg�cio relacionada a Campos
 * duplicados.
 * 
 * @author Rog�rio
 * @since Apr 8, 2006
 */
public class ValidatorInvalidValueException extends ValidatorFieldException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para a classe.
	 */
	public ValidatorInvalidValueException() {
		super();
	}

	/**
	 * Contrutor com par�metro para a super classe.
	 * 
	 * @param String
	 *            field
	 */
	public ValidatorInvalidValueException(String field) {
		super(field, null);
	}

}
