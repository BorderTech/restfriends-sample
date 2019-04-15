package com.restfriends.sample.api.exception;

import com.github.bordertech.restfriends.exception.RestBusinessException;

/**
 * Business checked exception.
 */
public class SampleBusinessException extends RestBusinessException {

	/**
	 * @param message the exception message
	 * @param ex the original exception
	 */
	public SampleBusinessException(final String message, final Exception ex) {
		super(message, ex);
	}

	/**
	 * @param message the exception message
	 */
	public SampleBusinessException(final String message) {
		super(message);
	}

}
