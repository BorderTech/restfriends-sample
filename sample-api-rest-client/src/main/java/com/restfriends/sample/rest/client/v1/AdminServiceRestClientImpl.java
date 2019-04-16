package com.restfriends.sample.rest.client.v1;

import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentResponse;
import com.restfriends.sample.api.model.types.DocumentType;
import com.restfriends.sample.api.model.types.StatusType;
import com.restfriends.sample.api.rest.v1.model.DocumentRequestDetails;
import com.restfriends.sample.api.rest.v1.model.ImageRequestDetails;
import com.restfriends.sample.api.rest.v1.model.StatusRequestDetails;
import com.restfriends.sample.api.service.AdminService;
import com.restfriends.sample.rest.client.jersey.v1.invoker.ApiException;

/**
 * Service implementation that calls Swagger generated REST Client.
 */
public class AdminServiceRestClientImpl extends AbstractRestClient implements AdminService {

	@Override
	public ApplicationResponse updateApplicationStatus(final String applicationId, final StatusType statusType) throws SampleBusinessException {
		try {
			return getApi().updateApplicationStatus(applicationId, new StatusRequestDetails(statusType));
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public AttachmentResponse updateDocumentType(final String applicationId, final String attachmentKey, final DocumentType documentType) throws SampleBusinessException {
		try {
			return getApi().updateDocumentType(applicationId, attachmentKey, new DocumentRequestDetails(documentType));
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public AttachmentResponse saveRedactImage(final String applicationId, final String attachmentKey, final byte[] image)
			throws SampleBusinessException {
		try {
			return getApi().saveRedactImage(applicationId, attachmentKey, new ImageRequestDetails(image));
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

}
