package com.restfriends.sample.api.service;

import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentResponse;
import com.restfriends.sample.api.model.types.DocumentType;
import com.restfriends.sample.api.model.types.StatusType;

/**
 * Services required by the attachments ADMIN.
 */
public interface AdminService {

	/**
	 * Update the application status.
	 *
	 * @param applicationId the application key
	 * @param statusType the status type to change to
	 * @return the updated application
	 * @throws SampleBusinessException a business exception processing request
	 */
	ApplicationResponse updateApplicationStatus(final String applicationId, final StatusType statusType)
			throws SampleBusinessException;

	/**
	 * Update the document type on an attachment.
	 *
	 * @param applicationId the application key
	 * @param attachmentKey the attachment to update
	 * @param documentType the document type to change to
	 * @return the updated attachment
	 * @throws SampleBusinessException a business exception processing request
	 */
	AttachmentResponse updateDocumentType(final String applicationId, final String attachmentKey, final DocumentType documentType)
			throws SampleBusinessException;

	/**
	 * Update an attachment as redacted.
	 *
	 * @param applicationId the application key
	 * @param attachmentKey the attachment that has been redacted
	 * @param image the updated image
	 * @return data response that action was successful
	 * @throws SampleBusinessException a business exception processing request
	 */
	AttachmentResponse saveRedactImage(final String applicationId, final String attachmentKey, final byte[] image)
			throws SampleBusinessException;

}
