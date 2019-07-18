package com.restfriends.sample.api.model.response;

import com.github.bordertech.restfriends.envelope.impl.DataResponse;
import com.restfriends.sample.api.model.AttachmentContent;

/**
 * Attachment Content Data Response.
 */
public class AttachmentContentResponse extends DataResponse<AttachmentContent> {

	protected AttachmentContentResponse() {
		super();
	}

	public AttachmentContentResponse(final AttachmentContent content) {
		super(content);
	}

}
