package com.restfriends.sample.rest.client.v1;

import com.restfriends.sample.api.exception.SampleSystemException;
import com.restfriends.sample.rest.client.jersey.v1.api.SampleServiceApi;
import com.restfriends.sample.rest.client.jersey.v1.invoker.ApiException;
import com.restfriends.sample.rest.client.v1.helper.DefaultApi;

/**
 * Provide the default Swagger API implementation.
 */
public abstract class AbstractRestClient {

	/**
	 * @return the Swagger API
	 */
	protected SampleServiceApi getApi() {
		return new DefaultApi();
	}

	/**
	 * Handle the API exception.
	 *
	 * @param e the API exception
	 * @return the system exception
	 */
	protected SampleSystemException handleException(final ApiException e) {
		return new SampleSystemException(e.getMessage(), e);
	}

}
