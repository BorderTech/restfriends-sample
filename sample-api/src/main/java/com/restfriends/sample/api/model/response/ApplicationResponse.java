package com.restfriends.sample.api.model.response;

import com.github.bordertech.restfriends.envelope.DataResponse;
import com.restfriends.sample.api.model.Application;

/**
 * Application Data Response.
 */
public class ApplicationResponse extends DataResponse<Application> {

	protected ApplicationResponse() {
		super();
	}

	public ApplicationResponse(final Application application) {
		super(application);
	}

}
