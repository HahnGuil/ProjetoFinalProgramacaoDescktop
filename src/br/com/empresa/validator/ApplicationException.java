package br.com.empresa.validator;

/**
 * Classe de tratamento de exceptions da Factory de Business.
 * 
 * @author Rog�rio Cortina
 * @since 01/02/2007
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	private String reason;

	private Throwable cause;

	/**
	 * Construtor default;
	 * 
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * Construtor com apenas um par�metro de mensagem.
	 * 
	 * @param message Mensagem do erro.
	 */
	public ApplicationException(String message) {
		reason = message;
	}

	/**
	 * Construtor utilizado para construir a mensagem gen�rica e as
	 * exceptions encapsuladas.
	 * 
	 * @param message Mensagem descritiva do erro.
	 * @param cause Exception encapsulada.
	 */
	public ApplicationException(String message, Throwable cause) {
		reason = message;
		this.cause = cause;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
