package br.com.empresa.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

	private static String key = "IWtper28seEzzzz9"; // 128 bit key
	private static String initVector = "RandomInitVector"; // 16 bytes IV

	/**
	 * Retorna o cálculo de hash em <i>Hexadecimal</i> gerado para um texto, após
	 * ser processado pelo algoritmo <b>SHA-1</b> do tipo <i>Message Digest</i>.
	 * 
	 * @param text - Texto a ser criptografado
	 * @return Hash resultante após o processamento <b>SHA-1</b>
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncryptedMessageDigest(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// logger.debug("Acessando "+logger.getName()+".getEncryptedMessageDigest(String)");

		return getEncryptedMessageDigest(text, "SHA-1");
	}

	/**
	 * Retorna o cálculo de hash em <i>Hexadecimal</i> gerado para um texto, após
	 * ser processado por um algoritmo do tipo <i>Message Digest</i>.
	 * 
	 * @param text - Texto a ser criptografado
	 * @param algorithm - Tipo de algoritmo a ser processado
	 * @return Hash resultante após o processamento
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncryptedMessageDigest(String text, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// logger.debug("Acessando "+logger.getName()+".getEncryptedMessageDigest(String, String)");

		byte[] hashedText = processMessageDigest(text, algorithm);

		StringBuffer result = new StringBuffer((hashedText.length * 2));

		for (byte val : hashedText) {
			if ((val & 0xff) < 0x10) {
				result.append("0");
			}
			result.append(Integer.toHexString(val & 0xff));
		}

		return result.toString();
	}

	/**
	 * Retorna o cálculo de hash gerado para um texto, após
	 * ser processado por um algoritmo do tipo <i>Message Digest</i>.
	 * 
	 * @param text - Texto a ser criptografado
	 * @param algorithm - Tipo de algoritmo a ser processado
	 * @return Array de bytes com o resultado do processamento do algoritmo
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] processMessageDigest(String text, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// logger.debug("Acessando "+logger.getName()+".proccessMessageDigest(String, String)");

		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		messageDigest.update(text.getBytes());

		byte[] hashedText = messageDigest.digest();

		return hashedText;
	}

	/**
	 * Criptografa um texto qualquer de modo que possa ser descriptografado posteriormente.
	 * 
	 * Essa função utiliza a classe Cipher com o método criptográfico AES.
	 * 
	 * @param value
	 * @return
	 */
	public static String criptografar(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(encrypted);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Descriptografa um texto qualquer que foi previamente critografado pela função dessa classe.
	 * 
	 * @param encrypted
	 * @return
	 */
	public static String descriptografar(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			Base64.Decoder decoder = Base64.getDecoder();

			byte[] original = cipher.doFinal(decoder.decode(encrypted));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
