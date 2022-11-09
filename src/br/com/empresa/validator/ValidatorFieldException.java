package br.com.empresa.validator;

/**
 * Classe business para tratar exce��es de Valida��o.
 * 
 * @author Jonatan
 * @since Apr 7, 2006
 */
public class ValidatorFieldException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	private String field;

	private String message;

	private Throwable cause;

	/**
	 * Construtor default;
	 * 
	 */
	public ValidatorFieldException() {
		super();
	}

	/**
	 * Construtor com a mensagem e a causa.
	 * 
	 * @param message
	 * @param cause
	 */
	public ValidatorFieldException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.cause = cause;
	}

	/**
	 * Construtor detalhado.
	 * 
	 * @param field
	 * @param message
	 * @param cause
	 */
	public ValidatorFieldException(String field, String message, Throwable cause) {
		super();

		this.field = field;
		this.message = message;
		this.cause = cause;
	}

	/**
	 * @return Returns the field.
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            The field to set.
	 */
	public void setField(String field) {
		this.field = field;
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

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
