package com.restfriends.sample.rest.client.v1.helper;

import com.restfriends.sample.rest.client.jersey.v1.api.SampleServiceApi;

/**
 * Default API invoker.
 */
public class DefaultApi extends SampleServiceApi {

	public DefaultApi() {
		super(new DefaultApiClient());
	}
}
