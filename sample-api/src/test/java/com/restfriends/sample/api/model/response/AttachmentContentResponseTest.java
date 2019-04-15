package com.restfriends.sample.api.model.response;

import com.restfriends.sample.api.model.AttachmentContent;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class AttachmentContentResponseTest {

	@Test
	public void constructorTest1() {

		AttachmentContentResponse attachmentContentResponse = new AttachmentContentResponse();
		assertThat(attachmentContentResponse.getData(), nullValue());
	}

	@Test
	public void constructorTest2() {
		AttachmentContent attachmentContent = new AttachmentContent(null, null, null, null);
		AttachmentContentResponse attachmentContentResponse = new AttachmentContentResponse(attachmentContent);
		assertThat(attachmentContentResponse.getData(), sameInstance(attachmentContent));
	}
}
