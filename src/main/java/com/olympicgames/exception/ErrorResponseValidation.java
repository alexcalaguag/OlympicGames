package com.olympicgames.exception;

import java.util.List;

/**
 * Clase representing the validation error returned by service rest
 * 
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 *
 */
public class ErrorResponseValidation {
	private int errorCode;
	private List<String> errors;

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
