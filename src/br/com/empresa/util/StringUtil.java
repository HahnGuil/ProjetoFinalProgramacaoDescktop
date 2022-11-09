package br.com.empresa.util;

import java.text.Normalizer;

/**
 * String utility methods.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */

public class StringUtil {

	private final static char SEPARATOR = '\n';
	public static String numeros = "0-9";
	public static String letras = "A-Z";
	public static String especiais1 = "ª_º_-";
	public static String especiais2 = "_._-";
	public static String especiais3 = "_ª_º_-_/_._,";
	public static String especiais4 = "_@_._-_";
	public static String especiais5 = "á_é_í_ó_ú_à_ã_õ_â_ê_ô_ç_Ç_Á_É_Í_Ó_Ú_Ã_Õ_Â_Ê_Ô_À";

	/**
	 * This is a string replacement method.
	 */
	public static String replaceString(String source, String oldStr, String newStr) {
		StringBuffer sb = new StringBuffer(source.length());
		int sind = 0;
		int cind = 0;
		while ((cind = source.indexOf(oldStr, sind)) != -1) {
			sb.append(source.substring(sind, cind));
			sb.append(newStr);
			sind = cind + oldStr.length();
		}
		sb.append(source.substring(sind));
		return sb.toString();
	}

	/**
	 * This method is used to insert HTML block dynamically
	 *
	 * @param source the HTML code to be processes
	 * @param bReplaceNl if true '\n' will be replaced by <br>
	 * @param bReplaceTag if true '<' will be replaced by &lt; and
	 *            '>' will be replaced by &gt;
	 * @param bReplaceQuote if true '\"' will be replaced by &quot;
	 */
	public static String formatHtml(String source, boolean bReplaceNl, boolean bReplaceTag, boolean bReplaceQuote) {

		StringBuffer sb = new StringBuffer();
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char c = source.charAt(i);
			switch (c) {
			case '\"':
				if (bReplaceQuote)
					sb.append("&quot;");
				else
					sb.append(c);
				break;

			case '<':
				if (bReplaceTag)
					sb.append("&lt;");
				else
					sb.append(c);
				break;

			case '>':
				if (bReplaceTag)
					sb.append("&gt;");
				else
					sb.append(c);
				break;

			case '\n':
				if (bReplaceNl) {
					if (bReplaceTag)
						sb.append("&lt;br&gt;");
					else
						sb.append("<br>");
				} else {
					sb.append(c);
				}
				break;

			case '\r':
				break;

			case '&':
				sb.append("&amp;");
				break;

			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * Pad string object
	 */
	public static String pad(String src, char padChar, boolean rightPad, int totalLength) {

		int srcLength = src.length();
		if (srcLength >= totalLength) {
			return src;
		}

		int padLength = totalLength - srcLength;
		StringBuffer sb = new StringBuffer(padLength);
		for (int i = 0; i < padLength; ++i) {
			sb.append(padChar);
		}

		if (rightPad) {
			return src + sb.toString();
		} else {
			return sb.toString() + src;
		}
	}

	/**
	 * Get hex string from byte array
	 */
	public static String toHexString(byte[] res) {
		StringBuffer sb = new StringBuffer(res.length << 1);
		for (int i = 0; i < res.length; i++) {
			String digit = Integer.toHexString(0xFF & res[i]);
			if (digit.length() == 1) {
				digit = '0' + digit;
			}
			sb.append(digit);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * Get byte array from hex string
	 */
	public static byte[] toByteArray(String hexString) {
		int arrLength = hexString.length() >> 1;
		byte buff[] = new byte[arrLength];
		for (int i = 0; i < arrLength; i++) {
			int index = i << 1;
			String digit = hexString.substring(index, index + 2);
			buff[i] = (byte) Integer.parseInt(digit, 16);
		}
		return buff;
	}

	/**
	 * Completa com zeros a esquerda.
	 * 
	 * @param entrada String a ser formatada.
	 * @param qtDigitos quantidade total de caracteres
	 * @return Valor com zeros a esquerda.
	 * 
	 *         Exemplo:
	 * 
	 *         Entrada = 200
	 *         Qt digitos = 5
	 * 
	 *         Sa�da = 00200
	 */
	public static String zeroLeft(String entrada, int qtDigitos) {
		if (entrada.length() >= qtDigitos)
			return entrada.substring(0, qtDigitos);

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < qtDigitos - entrada.length(); i++)
			buf.append('0');

		buf.append(entrada);

		return buf.toString();
	}
	
	/**
	 * Completa com zeros a direita.
	 * 
	 * @param entrada String a ser formatada.
	 * @param qtDigitos quantidade total de caracteres
	 * @return Valor com zeros a direita.
	 * 
	 *         Exemplo:
	 * 
	 *         Entrada = 200
	 *         Qt digitos = 5
	 * 
	 *         Sa�da = 20000
	 */
	public static String zeroRight(String entrada, int qtDigitos) {
		
		return pad(entrada, '0', true, qtDigitos);
	}

	/**
	 * Completa com espa�os vazios � direita.
	 * 
	 * @param entrada String a ser formatada.
	 * @param qtDigitos quantidade total de caracteres
	 * @return Valor com espa��s vazios � direita.
	 * 
	 *         Exemplo:
	 * 
	 *         Entrada = "ABCDE"
	 *         Qt digitos = 7
	 * 
	 *         Sa�da = "ABCDE "
	 */
	public static String vazioRight(String entrada, int qtDigitos) {
		if (entrada.length() >= qtDigitos)
			return entrada.substring(0, qtDigitos);

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < qtDigitos - entrada.length(); i++)
			buf.append(' ');

		return entrada + buf.toString();
	}

	/**
	 * Formata o texto removendo todos os caracteres que n�o s�o permitidos no arquivo de entrega.
	 * 
	 * @param texto Conte�do.
	 * @param upperCase Se TRUE, converte o conte�do p/ mai�sculo.
	 * @param acentuacao Se TRUE, remove � acentua��o do conte�do.
	 * @param qtdMax Se diferente de null e maior que zero, trunca o conte�do conforme a quantidade informada.
	 * @param caracteres Se TRUE, aceita caracteres de A-Z no conte�do.
	 * @param numeros Se TRUE, aceita caracteres de 0-9 no conte�do.
	 * @param especiais Caracteres especiais que s�o aceitos no conte�do.
	 * @return String contendo o conte�do formatado de acordo com os par�metros.
	 * @throws BOException
	 */
	public static String removeCaracteresEspeciais(String texto, boolean upperCase, boolean acentuacao, Integer qtdMax, boolean caracteres, boolean numeros,
			String especiais) {

		// SE O TEXTO N�O FOR NULO
		if (texto != null) {

			// SE FOR PARA CONVERTER EM MAI�SCULO.
			if (upperCase) {
				texto = texto.toUpperCase();
			}

			// REMOVE ACENTUA��O DO TEXTO.
			if (acentuacao) {
				texto = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
			}

			String pattern = " ";

			// ESPECIFICA QUE DESEJA LETRAS.
			if (caracteres) {
				if (upperCase)
					pattern = pattern + "A-Z";
				else
					pattern = pattern + "a-zA-Z";
			}

			// ESPECIFICA QUE DESEJA N�MEROS.
			if (numeros) {
				pattern = pattern + "_" + "0-9";
			}

			// ESPECIFICA QUE DESEJA UM CONJUNTO DETERMINADO DE CARACTERES ESPECIAIS.
			if (especiais != null) {
				pattern = pattern + "_" + especiais;
			}

			// PADR�O MONTADO.
			pattern = "[^" + pattern + "]";

			// APLICA O PADR�O.
			texto = texto.replaceAll(pattern, "");

			// REMOVE MAIS DE UM CARACTERE EM BRANCO EM UMA MESMA FRASE.
			texto = texto.replaceAll("\\s+", " ");

			// TRUNCA O TEXTO.
			if (qtdMax != null && qtdMax > 0 && texto.length() > qtdMax) {
				texto = texto.substring(0, qtdMax);
			}

			// RETIRA CARACTERES VAZIOS DAS EXTREMIDADES
			texto = texto.trim();
		}

		return texto;
	}

}