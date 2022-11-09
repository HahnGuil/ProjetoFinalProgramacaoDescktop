package br.com.empresa.validator;

/**
 * Classe de valida��o de Carteiras nacionais de sa�de.
 * 
 * @author Anderson Willian Zanelatto
 * @since 19/07/2012
 * 
 */
public class CNSValidator implements IValidator {

	public void validate(Object object) throws ValidatorInvalidValueException, ValidatorFieldException {

		if (object == null || object instanceof String == false) {
			throw new ValidatorInvalidValueException("Valor inv�lido.");
		}

		String cns = (String) object;

		if (cns.trim().length() != 15) {
			throw new ValidatorInvalidValueException("N�mero inv�lido.");
		}

		char primeiroDigito = cns.charAt(0);

		if (primeiroDigito == '1' || primeiroDigito == '2') {
			this.validaCNS12(cns);
		} else if (primeiroDigito == '7' || primeiroDigito == '8' || primeiroDigito == '9') {
			this.validaCNS789(cns);
		} else {
			throw new ValidatorInvalidValueException("N�mero inv�lido.");
		}

	}

	/**
	 * Valida CNSs que iniciam com os d�gitos 1 ou 2
	 * 
	 * @param cns
	 * @throws ValidatorInvalidValueException
	 * @throws ValidatorFieldException
	 */
	public void validaCNS12(String cns) throws ValidatorInvalidValueException, ValidatorFieldException {

		float soma;
		float resto, dv;
		String pis = new String("");
		String resultado = new String("");
		pis = cns.substring(0, 11);

		soma = ((Integer.valueOf(pis.substring(0, 1)).intValue()) * 15) + ((Integer.valueOf(pis.substring(1, 2)).intValue()) * 14)
				+ ((Integer.valueOf(pis.substring(2, 3)).intValue()) * 13) + ((Integer.valueOf(pis.substring(3, 4)).intValue()) * 12)
				+ ((Integer.valueOf(pis.substring(4, 5)).intValue()) * 11) + ((Integer.valueOf(pis.substring(5, 6)).intValue()) * 10)
				+ ((Integer.valueOf(pis.substring(6, 7)).intValue()) * 9) + ((Integer.valueOf(pis.substring(7, 8)).intValue()) * 8)
				+ ((Integer.valueOf(pis.substring(8, 9)).intValue()) * 7) + ((Integer.valueOf(pis.substring(9, 10)).intValue()) * 6)
				+ ((Integer.valueOf(pis.substring(10, 11)).intValue()) * 5);

		resto = soma % 11;
		dv = 11 - resto;

		if (dv == 11) {
			dv = 0;
		}

		if (dv == 10) {
			soma = ((Integer.valueOf(pis.substring(0, 1)).intValue()) * 15) + ((Integer.valueOf(pis.substring(1, 2)).intValue()) * 14)
					+ ((Integer.valueOf(pis.substring(2, 3)).intValue()) * 13) + ((Integer.valueOf(pis.substring(3, 4)).intValue()) * 12)
					+ ((Integer.valueOf(pis.substring(4, 5)).intValue()) * 11) + ((Integer.valueOf(pis.substring(5, 6)).intValue()) * 10)
					+ ((Integer.valueOf(pis.substring(6, 7)).intValue()) * 9) + ((Integer.valueOf(pis.substring(7, 8)).intValue()) * 8)
					+ ((Integer.valueOf(pis.substring(8, 9)).intValue()) * 7) + ((Integer.valueOf(pis.substring(9, 10)).intValue()) * 6)
					+ ((Integer.valueOf(pis.substring(10, 11)).intValue()) * 5) + 2;

			resto = soma % 11;
			dv = 11 - resto;
			resultado = pis + "001" + String.valueOf((int) dv);
		} else {
			resultado = pis + "000" + String.valueOf((int) dv);
		}

		if (!cns.equals(resultado)) {
			throw new ValidatorInvalidValueException("N�mero inv�lido.");
		}
	}

	/**
	 * Valida CNSs que iniciam com os d�gitos 7, 8 ou 9
	 * 
	 * @param cns
	 * @throws ValidatorInvalidValueException
	 * @throws ValidatorFieldException
	 */
	public void validaCNS789(String cns) throws ValidatorInvalidValueException, ValidatorFieldException {

		float resto, soma;

		soma = ((Integer.valueOf(cns.substring(0, 1)).intValue()) * 15) + ((Integer.valueOf(cns.substring(1, 2)).intValue()) * 14)
				+ ((Integer.valueOf(cns.substring(2, 3)).intValue()) * 13) + ((Integer.valueOf(cns.substring(3, 4)).intValue()) * 12)
				+ ((Integer.valueOf(cns.substring(4, 5)).intValue()) * 11) + ((Integer.valueOf(cns.substring(5, 6)).intValue()) * 10)
				+ ((Integer.valueOf(cns.substring(6, 7)).intValue()) * 9) + ((Integer.valueOf(cns.substring(7, 8)).intValue()) * 8)
				+ ((Integer.valueOf(cns.substring(8, 9)).intValue()) * 7) + ((Integer.valueOf(cns.substring(9, 10)).intValue()) * 6)
				+ ((Integer.valueOf(cns.substring(10, 11)).intValue()) * 5) + ((Integer.valueOf(cns.substring(11, 12)).intValue()) * 4)
				+ ((Integer.valueOf(cns.substring(12, 13)).intValue()) * 3) + ((Integer.valueOf(cns.substring(13, 14)).intValue()) * 2)
				+ ((Integer.valueOf(cns.substring(14, 15)).intValue()) * 1);

		resto = soma % 11;

		if (resto != 0) {
			throw new ValidatorInvalidValueException("N�mero inv�lido.");
		}
	}
}
