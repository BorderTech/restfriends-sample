package com.restfriends.sample.api.mock;

import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.Application;
import com.restfriends.sample.api.model.Attachment;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentResponse;
import com.restfriends.sample.api.model.types.DocumentType;
import com.restfriends.sample.api.model.types.StatusType;
import com.restfriends.sample.api.service.AdminService;
import com.restfriends.sample.api.service.ApplicationService;
import java.util.Date;
import java.util.Objects;

/**
 * Mock Admin Service.
 */
public class AdminServiceMockImpl implements AdminService {

	private static final ApplicationService APPL = new ApplicationServiceMockImpl();

	@Override
	public ApplicationResponse updateApplicationStatus(final String applicationId, final StatusType statusType) throws SampleBusinessException {
		Application appl = getApplication(applicationId);
		appl.setStatus(statusType);
		updateApplication(appl);
		return new ApplicationResponse(appl);
	}

	@Override
	public AttachmentResponse updateDocumentType(final String applicationId, final String attachmentKey, final DocumentType documentType) throws SampleBusinessException {
		Attachment item = getAttachment(applicationId, attachmentKey);
		item.setDocumentType(documentType);
		updateAttachment(item);
		return new AttachmentResponse(item);
	}

	@Override
	public AttachmentResponse saveRedactImage(final String applicationId, final String attachmentKey, final byte[] image)
			throws SampleBusinessException {
		Attachment item = getAttachment(applicationId, attachmentKey);
		// Put Image in MOCK Cache
		String key = applicationId + "-" + attachmentKey;
		MockDataUtil.saveRedactImage(key, image);
		updateAttachment(item);
		// Image has changed
		item.setSavedTimestamp(new Date());
		return new AttachmentResponse(item);
	}

	private Application getApplication(final String applicationId) throws SampleBusinessException {
		Application appl = APPL.retrieveApplication(applicationId).getData();
		if (appl == null) {
			throw new SampleBusinessException("No Application for [" + applicationId + "].");
		}
		return appl;
	}

	private Attachment getAttachment(final String applicationId, final String attachmentKey) throws SampleBusinessException {
		Application appl = getApplication(applicationId);
		// Find Attachment
		for (Attachment attachment : appl.getAttachments()) {
			if (Objects.equals(attachment.getAttachmentKey(), attachmentKey)) {
				return attachment;
			}
		}
		throw new SampleBusinessException("No attachment for [" + attachmentKey + "].");
	}

	private void updateApplication(final Application appl) {
		appl.setUpdatedTimestamp(new Date());
	}

	private void updateAttachment(final Attachment item) {
		item.setDescription(item.getDescription() + "-upd");
		item.setSavedTimestamp(new Date());
	}

}
