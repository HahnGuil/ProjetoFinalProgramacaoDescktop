package br.com.empresa.validator;

import java.util.List;

/**
 * Classe business para tratar exce��es de Valida��o.
 * 
 * @author Jonatan
 * @since Apr 7, 2006
 */
public class ValidatorException extends ApplicationException {

	private static final long serialVersionUID = 1L;
	private List<ValidatorFieldException> erros;

	/**
	 * Construtor default;
	 * 
	 */
	public ValidatorException() {
		super();
	}

	/**
	 * Construtor com vetor de erros como par�metro.
	 * 
	 * @param erros
	 */
	public ValidatorException(List<ValidatorFieldException> erros) {
		super();
		this.erros = erros;
	}

	public List<ValidatorFieldException> getErros() {
		return erros;
	}

	public void setErros(List<ValidatorFieldException> erros) {
		this.erros = erros;
	}

}
