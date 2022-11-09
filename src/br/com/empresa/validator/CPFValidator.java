package br.com.empresa.validator;

/**
 * Validador de CPF
 */
public class CPFValidator implements IValidator {

	private static int[] cpf;

	/**
	 * Constante utilizada pra determinar o campo CPF.
	 */
	public static final String FIELD_CPF = "CPF";

	/**
	 * Array com os multiplicadores usados para a verifica��o do CPF
	 */
	private static final int[] cpfVerifier = new int[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	/**
	 * Efetua a valida��o do n�mero do CPF
	 */
	public void validate(Object object) throws ValidatorInvalidValueException, ValidatorFormatException {

		if (!(object instanceof String))
			throw new ValidatorInvalidValueException(FIELD_CPF);

		String cpfStr = (String) object;
		cpf = verifyNumber(cpfStr);

		int firstVerifier = calculateFirstVerifier();
		int secondVerifier = calculateSecondVerifier();

		if ((firstVerifier != cpf[cpf.length - 2]) || (secondVerifier != cpf[cpf.length - 1])) {
			throw new ValidatorInvalidValueException(FIELD_CPF);
		}
	}
	
	/**
	 * Efetua a valida��o do n�mero do CPF.
	 * 
	 * @param cpf
	 * @throws Exception
	 */
	public static void validate(String numeroCPF) throws Exception {
		
		numeroCPF = numeroCPF.replaceAll("-", "");
		
		cpf = verifyNumber(numeroCPF);

		int firstVerifier = calculateFirstVerifier();
		int secondVerifier = calculateSecondVerifier();

		if ((firstVerifier != cpf[cpf.length - 2]) || (secondVerifier != cpf[cpf.length - 1])) {
			throw new ValidatorInvalidValueException(FIELD_CPF);
		}
	}

	/**
	 * Faz a verifica��o se o n�mero do CPF est� dentro das especifica��es
	 * (possuir 11 d�gitos e somente n�meros)
	 */
	private static int[] verifyNumber(String cpfStr) throws ValidatorFormatException {
		int[] cpf = new int[11];
		if (cpfStr.length() != 11) {
			throw new ValidatorFormatException(FIELD_CPF);
		}
		try {
			for (int i = 0; i < cpf.length; i++) {
				cpf[i] = Integer.parseInt(cpfStr.charAt(i) + "");
			}
		} catch (NumberFormatException nfe) {
			throw new ValidatorFormatException(FIELD_CPF);
		}
		return cpf;
	}

	/**
	 * Faz o c�lculo para obten��o do Primeiro d�gito verificador do CPF
	 */
	private static int calculateFirstVerifier() {
		int aux = 0, result, resto;
		int dividendo = 11;

		for (int i = 0; i < cpf.length - 2; i++) {
			aux += cpf[i] * cpfVerifier[i + 1];
		}

		resto = aux % dividendo;

		if (resto < 2)
			result = 0;
		else
			result = dividendo - resto;

		return result;
	}

	/**
	 * Faz o c�lculo para obten��o do Segundo d�gito verificador do CPF
	 */
	private static int calculateSecondVerifier() {
		int aux = 0, result, resto;
		int dividendo = 11;

		for (int i = 0; i < cpf.length - 1; i++) {
			aux += cpf[i] * cpfVerifier[i];
		}

		resto = aux % dividendo;

		if (resto < 2)
			result = 0;
		else
			result = dividendo - resto;

		return result;
	}
}
