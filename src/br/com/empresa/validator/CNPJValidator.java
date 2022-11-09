package br.com.empresa.validator;


public class CNPJValidator implements IValidator {

	private int[] cnpj;

	/**
	 * Constante utilizada pra determinar o campo CNPJ.
	 */
	public static final String FIELD_CNPJ = "CNPJ";

	/**
	 * Array com os multiplicadores usados para a verifica��o do CNPJ
	 */
	private static final int[] cnpjVerifier = new int[] { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	/**
	 * Efetua a valida��o do n�mero do CNPJ
	 */
	public void validate(Object object) throws ValidatorFormatException, ValidatorInvalidValueException {

		if (!(object instanceof String))
			throw new ValidatorInvalidValueException(FIELD_CNPJ);

		String cnpjStr = (String) object;
		cnpj = verifyCNPJNumber(cnpjStr);

		int firstVerifier = calculateFirstVerifier();
		int secondVerifier = calculateSecondVerifier();

		if ((firstVerifier != cnpj[cnpj.length - 2]) || (secondVerifier != cnpj[cnpj.length - 1])) {
			throw new ValidatorInvalidValueException(FIELD_CNPJ);
		}
	}

	/**
	 * Faz a verifica��o se o n�mero do CNPJ est� dentro das especifica��es
	 * (possuir 14 d�gitos e somente n�meros)
	 */
	private int[] verifyCNPJNumber(String cnpjStr) throws ValidatorFormatException {
		int[] cnpj = new int[14];
		if (cnpjStr.length() != 14) {
			throw new ValidatorFormatException(FIELD_CNPJ);
		}
		try {
			for (int i = 0; i < cnpj.length; i++) {
				cnpj[i] = Integer.parseInt(cnpjStr.charAt(i) + "");
			}
		} catch (NumberFormatException nfe) {
			throw new ValidatorFormatException(FIELD_CNPJ);
		}
		return cnpj;
	}

	/**
	 * Faz o c�lculo para obten��o do Primeiro d�gito verificador do CNPJ
	 */
	private int calculateFirstVerifier() {
		int aux = 0, result, resto;
		int dividendo = 11;

		for (int i = 0; i < cnpj.length - 2; i++) {
			aux += cnpj[i] * cnpjVerifier[i + 1];
		}

		resto = aux % dividendo;

		if (resto < 2)
			result = 0;
		else
			result = dividendo - resto;

		return result;
	}

	/**
	 * Faz o c�lculo para obten��o do Segundo d�gito verificador do CNPJ
	 */
	private int calculateSecondVerifier() {
		int aux = 0, result, resto;
		int dividendo = 11;

		for (int i = 0; i < cnpj.length - 1; i++) {
			aux += cnpj[i] * cnpjVerifier[i];
		}

		resto = aux % dividendo;

		if (resto < 2)
			result = 0;
		else
			result = dividendo - resto;

		return result;
	}
}
