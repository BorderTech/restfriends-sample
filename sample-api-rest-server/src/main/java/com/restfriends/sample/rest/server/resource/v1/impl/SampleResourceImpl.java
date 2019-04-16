package com.restfriends.sample.rest.server.resource.v1.impl;

import com.github.bordertech.didums.Didums;
import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentContentResponse;
import com.restfriends.sample.api.model.response.AttachmentResponse;
import com.restfriends.sample.api.model.types.DocumentType;
import com.restfriends.sample.api.model.types.StatusType;
import com.restfriends.sample.api.rest.v1.SampleResource;
import com.restfriends.sample.api.service.AdminService;
import com.restfriends.sample.api.service.ApplicationService;

/**
 * Sample REST Resource call a backing service implementation.
 */
public class SampleResourceImpl implements SampleResource {

	private static final AdminService ADMIN = Didums.getService(AdminService.class);
	private static final ApplicationService APPL = Didums.getService(ApplicationService.class);

	@Override
	public ApplicationResponse retrieveApplication(final String applicationId) throws SampleBusinessException {
		return APPL.retrieveApplication(applicationId);
	}

	@Override
	public AttachmentContentResponse retrieveAttachmentContent(final String applicationId, final String attachmentId) throws SampleBusinessException {
		return APPL.retrieveAttachmentContent(applicationId, attachmentId);
	}

	@Override
	public ApplicationResponse updateApplicationStatus(final String applicationId, final StatusType statusType) throws SampleBusinessException {
		return ADMIN.updateApplicationStatus(applicationId, statusType);
	}

	@Override
	public AttachmentResponse updateDocumentType(final String applicationId, final String attachmentKey, final DocumentType documentType) throws SampleBusinessException {
		return ADMIN.updateDocumentType(applicationId, attachmentKey, documentType);
	}

	@Override
	public AttachmentResponse saveRedactImage(final String applicationId, final String attachmentKey, final byte[] image) throws SampleBusinessException {
		return ADMIN.saveRedactImage(applicationId, attachmentKey, image);
	}

}
