package br.com.empresa.validator;

/**
 * Classe de validação de certição de nascimento, casamento e óbito.
 * 
 * @author Rogério Cortina
 * @since 12/05/2014
 * 
 *        Obtido a partir do link http://ghiorzi.org/DVnew.htm
 * 
 *        O Número de Matrícula tem a configuração XXXXXX.XX.XX.XXXX.X.XXXXX.XXX.XXXXXXX-XX,
 *        onde o 1º grupo de 6 dígitos identifica o Cartório, o 2º grupo de 2 dígitos identifica o acervo, o
 *        3º grupo de 2 dígitos identifica o serviço do registro civil das pessoas naturais, o 4º grupo de 4 dígitos
 *        identifica o ano do registro, o 5º grupo de 1 dígito identifica o tipo do livro de registro,
 *        o 6º grupo de 5 dígitos identifica o número do livro, o 7º grupo de 3 dígitos identifica o número da folha do livro
 *        e o 8º grupo de 7 dígitos identifica o Número de Registro.
 *        Finalmente o 9º grupo de 2 dígitos corresponde ao DV
 *        (Dígito de Verificação), cujo cálculo obedece ao seguinte esquema, dentro do critério de DV MÓDULO 11 já conhecido:
 * 
 *        1 0 4 5 3 9 0 1 5 5 2 0 1 3 1 0 0 0 1 2 0 2 1 0 0 0 0 1 2 3 = 2
 *        x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
 *        2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9
 *        -----------------------------------------------------------------------------------------
 *        2+ 0+16+25+18+63+ 0+ 9+**+**+ 2+ 0+ 3+12+**+ 0+ 0+ 0+ 9+20+ 0+ 2+ 2+ 0+ 0+ 0+ 0+ 7+16+27 = 233
 * 
 *        233÷11=21, com resto 2 (este é o 1º dígito do DV) - Nota: se o resto for "10", o DV será "1"
 * 
 *        1 0 4 5 3 9 0 1 5 5 2 0 1 3 1 0 0 0 1 2 0 2 1 0 0 0 0 1 2 3 2 = 1
 *        x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
 *        1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9 10 0 1 2 3 4 5 6 7 8 9
 *        --------------------------------------------------------------------------------------------
 *        1+ 0+12+20+15+54+ 0+ 8+**+**+ 0+ 0+ 2+ 9+**+ 0+ 0+ 0+ 8+18+ 0+ 0+ 1+ 0+ 0+ 0+ 0+ 6+14+24+18 = 210
 * 
 *        210÷11=19, com resto 1 (este é o 2º dígito do DV) - Nota: se o resto for "10", o DV será "1"
 * 
 *        ** esses dígitos, do 3º e do 5º grupo, são desprezados na formação do DV
 * 
 *        Portanto, o Número de Matrícula+DV = 104539.01.55.2013.1.00012.021.0000123-21
 * 
 */
public class CertidaoValidator implements IValidator {

	private static final int[] multiplicador = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	@Override
	public void validate(Object object) throws ValidatorInvalidValueException, ValidatorFieldException {
		// validateMEC(object);
		validateIExpertise(object);
	}

	/**
	 * Validador da IExpertise.
	 * 
	 * @param object
	 * @throws ValidatorInvalidValueException
	 * @throws ValidatorFieldException
	 */
	public void validateIExpertise(Object object) throws ValidatorInvalidValueException, ValidatorFieldException {

		if (!(object instanceof String))
			throw new ValidatorInvalidValueException("Nº Certidão");

		String numeroStr = (String) object;
		if (numeroStr.length() < 31) {
			throw new ValidatorInvalidValueException("Número inválido.");
		} else if (numeroStr.substring(6, 8).equals("01") == false && numeroStr.substring(6, 8).equals("02") == false) {
			throw new ValidatorInvalidValueException("Código do acervo.");
			/*
			 * Código do acervo (7º e 8º números da matrícula) sendo:
			 * "01" para acervo próprio e
			 * "02" para os acervos incorporados até 31/12/2009, último dia antes
			 * da implementação do Código Nacional por todos os registradores
			 * civis das pessoas naturais (nesse caso os seis primeiros números
			 * serão aqueles da serventia incorporadora).
			 * As certidões extraídas de acervos incorporados a partir de 1º de
			 * janeiro de 2010 (acervo de serventias que já possuíam código
			 * nacional próprio por ocasião da incorporação) utilizarão o código da
			 * serventia incorporada e o código de acervo 01;
			 */
		} else if (numeroStr.substring(15, 16).matches("(4|5|6)")) {
			throw new ValidatorInvalidValueException("Código do acervo.");
		} else if (numeroStr.substring(8, 10).equals("55") == false) {
			throw new ValidatorInvalidValueException("Tipo de serviço prestado(deve ser 55).");
		}

		int[] multiplicando = new int[30];

		multiplicando[0] = (int)numeroStr.charAt(29);

		// CONVERTENDO O VALOR DE STRING PARA NUMÉRICO.
		try {
			int j = 29;
			multiplicando[0] = Integer.parseInt(numeroStr.substring(29, 30));
			for (int i = 1; i < 30; i++) {
				multiplicando[i] = Integer.parseInt(numeroStr.substring(j - 1, j));
				j--;
			}
		} catch (NumberFormatException ex) {
			throw new ValidatorFormatException();
		}

		Integer certPriDig = 0;
		Integer certSegDig = 0;
		Integer certDV = 0;
		String certDVStr = "";

		// REALIZANDO A MULTIPLICAÇÃO PARA ENCONTRAR O PRIMEIRO DIGITO VERIFICADOR.
		for (int i = 0; i < 30; i++) {
			certPriDig = certPriDig + (multiplicando[i] * multiplicador[i]);
		}

		certPriDig = certPriDig % 11;

		if (certPriDig == 10) {
			certPriDig = 1;
		}

		// REALIZANDO A MULTIPLICAÇÃO PARA ENCONTRAR O SEGUNDO DIGITO VERIFICADOR.
		certSegDig = certPriDig * multiplicador[0];
		for (int i = 0; i < 29; i++) {
			certSegDig = certSegDig + (multiplicando[i] * multiplicador[i + 1]);
		}
		certSegDig = certSegDig + multiplicando[29];

		certSegDig = certSegDig % 11;

		if (certSegDig == 10) {
			certSegDig = 1;
		}

		// FINALIZA O CALCULO DO DÍGITO VERIFICADOR.

		certDV = certPriDig * 10 + certSegDig;

		if (certDV == 0) {
			certDVStr = "00";
		} else {
			if (certDV > 0 && certDV < 10) {
				certDVStr = "0" + certDV;
			} else {
				certDVStr = String.valueOf(certDV);
			}
		}

		if (certDVStr.equals(numeroStr.trim().substring(30, numeroStr.length())) == false) {
			throw new ValidatorInvalidValueException("Nº Certidão");
		}

	}

	/**
	 * Valida certidão nova. Validação do MEC.
	 * 
	 * @param certidaoNova
	 * @return
	 */
	public void validateMEC(Object object) throws ValidatorInvalidValueException, ValidatorFieldException {

		if (!(object instanceof String))
			throw new ValidatorInvalidValueException("Nº Certidão");

		String numeroStr = (String) object;

		if (numeroStr.length() < 31) {
			throw new ValidatorInvalidValueException("Número inválido.");
		}

		// CONVERTENDO O VALOR DE STRING PARA NUMÉRICO E VERIFICANDO SE NÃO HÁ NÚMEROS ESTRANHOS.
		try {
			int j = 29;
			Integer.parseInt(numeroStr.substring(29, 30));
			for (int i = 1; i < 30; i++) {
				Integer.parseInt(numeroStr.substring(j - 1, j));
				j--;
			}
		} catch (NumberFormatException ex) {
			throw new ValidatorFormatException();
		}

		// Colocar a validação de cartorio ao validar nova certidão
		if (numeroStr.length() == 32) {
			if (numeroStr.substring(0, 30).matches("\\d+")) {
				if (numeroStr.substring(6, 8).equals("01") || numeroStr.substring(6, 8).equals("02")) {
					if (numeroStr.substring(8, 10).equals("55")) {
						int anoCertidao = Integer.parseInt(numeroStr.substring(10, 14));
						if (anoCertidao > 1904 && anoCertidao <= 2014) {
							if (!numeroStr.substring(15, 16).matches("(4|5|6)")) {
								if (numeroStr.substring(30, 32).equals("XX")) {
									return;
								} else {
									if (numeroStr.substring(30, 32).matches("\\d+")) {
										String digitosVerificadorUm = retornaModulo11(numeroStr.substring(0, 30));
										String digitosVerificadorDois = retornaModulo11(numeroStr.substring(0, 31));
										String digitosVerificadores = digitosVerificadorUm.concat(digitosVerificadorDois);

										if (numeroStr.substring(30, 32).equals(digitosVerificadores)) {
											return;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		throw new ValidatorInvalidValueException("Nº Certidão");
	}

	/**
	 * Calcula o módulo do dígito verificador.
	 * 
	 * @param certidaoNova
	 * @return
	 */
	public String retornaModulo11(String certidaoNova) {
		int soma = 0, numero = 0, resto = 0;
		for (int i = 0; i < certidaoNova.length(); i++) {
			numero = Integer.valueOf(certidaoNova.substring(i, i + 1)).intValue();
			soma += numero * ((certidaoNova.length() + 1) - i);
		}

		resto = (soma * 10) % 11;
		if (resto == 10) {
			resto = 1;
		}

		return String.valueOf(resto);
	}
}
