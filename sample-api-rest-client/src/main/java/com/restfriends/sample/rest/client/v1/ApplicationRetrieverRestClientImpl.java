package com.restfriends.sample.rest.client.v1;

import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentContentResponse;
import com.restfriends.sample.api.service.ApplicationService;
import com.restfriends.sample.rest.client.jersey.v1.invoker.ApiException;

/**
 * Service implementation that calls Swagger generated REST Client.
 */
public class ApplicationRetrieverRestClientImpl extends AbstractRestClient implements ApplicationService {

	@Override
	public ApplicationResponse retrieveApplication(final String applicationId) throws SampleBusinessException {
		try {
			return getApi().retrieveApplication(applicationId);
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public AttachmentContentResponse retrieveAttachmentContent(final String applicationId, final String attachmentKey)
			throws SampleBusinessException {
		try {
			return getApi().retrieveAttachmentContent(applicationId, attachmentKey);
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

}
