package br.com.empresa.validator;

public interface IValidator {

	/**
	 * Efetua a valida��o de algo.
	 */
	public void validate(Object object) throws ValidatorFieldException;

}