package com.restfriends.sample.api.model.response;

import com.github.bordertech.restfriends.envelope.DataResponse;
import com.restfriends.sample.api.model.Attachment;

public class AttachmentResponse extends DataResponse<Attachment> {

	protected AttachmentResponse() {
		super();
	}

	public AttachmentResponse(final Attachment attachment) {
		super(attachment);
	}

}
