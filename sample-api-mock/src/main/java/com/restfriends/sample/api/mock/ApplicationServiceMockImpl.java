package com.restfriends.sample.api.mock;

import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.exception.SampleSystemException;
import com.restfriends.sample.api.model.Application;
import com.restfriends.sample.api.model.Attachment;
import com.restfriends.sample.api.model.AttachmentContent;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentContentResponse;
import com.restfriends.sample.api.service.ApplicationService;
import java.util.Objects;

/**
 * Mock implementation for testing.
 */
public class ApplicationServiceMockImpl implements ApplicationService {

	@Override
	public ApplicationResponse retrieveApplication(final String applicationId)
			throws SampleBusinessException {
		if (applicationId == null) {
			throw new SampleBusinessException("A key must be provided.");
		}
		switch (applicationId) {
			case MockDataUtil.APPLICATION_SYSTEM_EXCEPTION_ID:
				throw new SampleSystemException("MOCK system error.");
			case MockDataUtil.APPLICATION_BUSINESS_EXCEPTION_ID:
				throw new SampleBusinessException("MOCK business error.");
			case MockDataUtil.APPLICATION_NOT_FOUND_ID:
				return new ApplicationResponse(null);
			default:
				break;
		}
		Application appl = MockDataUtil.getOrCreateApplication(applicationId);
		return new ApplicationResponse(appl);
	}

	@Override
	public AttachmentContentResponse retrieveAttachmentContent(final String applicationId, final String attachmentKey)
			throws SampleBusinessException {
		if (attachmentKey == null) {
			throw new IllegalArgumentException("An attachment key must be provided.");
		}
		if (applicationId == null) {
			throw new IllegalArgumentException("An application key must be provided.");
		}

		// Application Attachment
		if (attachmentKey.startsWith("appl")) {
			AttachmentContent content = MockDataUtil.getApplicationAttachment(attachmentKey);
			return new AttachmentContentResponse(content);
		}

		// Get the Application
		Application appl = retrieveApplication(applicationId).getData();
		Attachment attachment = null;

		// Find the attachment
		for (Attachment doc : appl.getAttachments()) {
			if (Objects.equals(attachmentKey, doc.getAttachmentKey())) {
				attachment = doc;
				break;
			}
		}
		if (attachment == null) {
			throw new IllegalStateException("Attachment not found");
		}

		AttachmentContent content = MockDataUtil.getAttachmentContent(attachment);
		return new AttachmentContentResponse(content);
	}

}
