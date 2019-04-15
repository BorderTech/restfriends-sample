package com.restfriends.sample.api.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class AttachmentContentTest {

	@Test
	public void testConstructor1() throws Exception {
		AttachmentContent applicationAttachment = new AttachmentContent();
		assertThat(applicationAttachment.getKey(), nullValue());
		assertThat(applicationAttachment.getBytes(), nullValue());
		assertThat(applicationAttachment.getFileName(), nullValue());
		assertThat(applicationAttachment.getMimeType(), nullValue());
	}

	@Test
	public void testConstructor2() throws Exception {
		byte[] bytes = "dummy".getBytes();
		AttachmentContent attachmentContent = new AttachmentContent("key", bytes, "fileName", "mimeType");
		assertThat(attachmentContent.getKey(), equalTo("key"));
		assertThat(attachmentContent.getBytes(), equalTo(bytes));
		assertThat(attachmentContent.getFileName(), equalTo("fileName"));
		assertThat(attachmentContent.getMimeType(), equalTo("mimeType"));
	}

	@Test
	public void testFields() throws Exception {
		byte[] bytes = "dummy".getBytes();
		AttachmentContent attachmentContent = new AttachmentContent();
		attachmentContent.setKey("key");
		attachmentContent.setBytes(bytes);
		attachmentContent.setFileName("fileName");
		attachmentContent.setMimeType("mimeType");
		assertThat(attachmentContent.getKey(), equalTo("key"));
		assertThat(attachmentContent.getBytes(), equalTo(bytes));
		assertThat(attachmentContent.getFileName(), equalTo("fileName"));
		assertThat(attachmentContent.getMimeType(), equalTo("mimeType"));
	}

	@Test
	public void equalsEqualsNull() throws Exception {
		AttachmentContent attachmentContent = new AttachmentContent();
		assertThat(attachmentContent.equals(null), equalTo(false));
	}

	@Test
	public void equalsEquals() throws Exception {
		AttachmentContent attachmentContent = new AttachmentContent();
		assertThat(attachmentContent.equals(attachmentContent), equalTo(true));
	}

	@Test
	public void equalsWrongInstance() throws Exception {
		AttachmentContent attachmentContent = new AttachmentContent();
		Object object = new Object();
		assertThat(attachmentContent.equals(object), equalTo(false));
	}

	@Test
	public void equalsSameData() throws Exception {
		AttachmentContent attachmentContent1 = new AttachmentContent();
		AttachmentContent attachmentContent2 = new AttachmentContent();
		assertThat(attachmentContent1.equals(attachmentContent2), equalTo(true));
	}

	@Test
	public void testHashCode() throws Exception {
		AttachmentContent attachment1 = new AttachmentContent();
		AttachmentContent attachment2 = new AttachmentContent();
		assertThat(attachment1.hashCode(), equalTo(attachment2.hashCode()));
	}

}
