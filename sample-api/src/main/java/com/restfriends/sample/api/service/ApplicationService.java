package com.restfriends.sample.api.service;

import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentContentResponse;

/**
 * Application retrieve service.
 */
public interface ApplicationService {

	/**
	 * Retrieve an application.
	 *
	 * @param applicationId the application ID to retrieve
	 * @return the application details
	 * @throws SampleBusinessException a business exception processing request
	 */
	ApplicationResponse retrieveApplication(final String applicationId)
			throws SampleBusinessException;

	/**
	 * Retrieve an attachment.
	 *
	 * @param applicationId the application ID
	 * @param attachmentKey the attachment ID to retrieve
	 * @return the attachment content
	 * @throws SampleBusinessException a business exception processing request
	 */
	AttachmentContentResponse retrieveAttachmentContent(final String applicationId, final String attachmentKey)
			throws SampleBusinessException;
}
