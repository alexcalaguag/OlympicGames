package com.olympicgames.exception;

/**
 * Exception related to business validations
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 *
 */
public class ValidateException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String errorKey;

	/**
	 * Construct an exception with the defined parameters
	 * @param errorMessage
	 * @param erroKey
	 */
	public ValidateException(String errorMessage,String erroKey) {
		super(errorMessage);
		this.setErrorMessage(errorMessage);
		this.setErrorKey(erroKey);
	}

	/**
	 * Construct default
	 */
	public ValidateException() {
		super();
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorKey
	 */
	public String getErrorKey() {
		return errorKey;
	}

	/**
	 * @param errorKey the errorKey to set
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
	

}
