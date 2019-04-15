package com.restfriends.sample.api.model.response;

import com.restfriends.sample.api.model.Attachment;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class AttachmentResponseTest {

	@Test
	public void constructorTest1() {

		AttachmentResponse attachmentResponse = new AttachmentResponse();
		assertThat(attachmentResponse.getData(), nullValue());
	}

	@Test
	public void constructorTest2() {
		Attachment attachment = new Attachment("A1", "K1");
		AttachmentResponse attachmentResponse = new AttachmentResponse(attachment);
		assertThat(attachmentResponse.getData(), sameInstance(attachment));
	}

}
