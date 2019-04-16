package com.restfriends.sample.api.exception;

import com.github.bordertech.restfriends.exception.RestSystemException;

/**
 * System unchecked exception.
 */
public class SampleSystemException extends RestSystemException {

	/**
	 * @param message the exception message
	 * @param ex the original exception
	 */
	public SampleSystemException(final String message, final Exception ex) {
		super(message, ex);
	}

	/**
	 * @param message the exception message
	 */
	public SampleSystemException(final String message) {
		super(message);
	}

}
