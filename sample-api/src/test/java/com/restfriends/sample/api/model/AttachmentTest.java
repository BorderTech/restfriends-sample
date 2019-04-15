package com.restfriends.sample.api.model;

import java.util.Date;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class AttachmentTest {

	@Test
	public void testConstructor1() throws Exception {
		Attachment attachment = new Attachment();
		assertThat(attachment.getAttachmentKey(), nullValue());
		assertThat(attachment.getApplicationId(), nullValue());
	}

	@Test
	public void testConstructor2() throws Exception {
		Attachment attachment = new Attachment("appId", "attachKey");
		assertThat(attachment.getApplicationId(), equalTo("appId"));
		assertThat(attachment.getAttachmentKey(), equalTo("attachKey"));
	}

	@Test
	public void testFields() throws Exception {
		Attachment attachment = new Attachment("appId", "attachKey");
		attachment.setFileName("fileName");
		attachment.setDescription("desc");
		Date date = new Date();
		attachment.setSavedTimestamp(date);
		attachment.setFileSize(2450L);
		assertThat(attachment.getFileName(), equalTo("fileName"));
		assertThat(attachment.getDescription(), equalTo("desc"));
		assertThat(attachment.getSavedTimestamp(), equalTo(date));
		assertThat(attachment.getFileSize(), equalTo(2450L));
	}

	@Test
	public void equalsEqualsNull() throws Exception {
		Attachment attachment = new Attachment();
		assertThat(attachment.equals(null), equalTo(false));
	}

	@Test
	public void equalsEquals() throws Exception {
		Attachment attachment = new Attachment();
		assertThat(attachment.equals(attachment), equalTo(true));
	}

	@Test
	public void equalsWrongInstance() throws Exception {
		Attachment attachment = new Attachment();
		Object object = new Object();
		assertThat(attachment.equals(object), equalTo(false));
	}

	@Test
	public void equalsSameData() throws Exception {
		Attachment attachment1 = new Attachment();
		Attachment attachment2 = new Attachment();
		assertThat(attachment1.equals(attachment2), equalTo(true));
	}

	@Test
	public void testHashCode() throws Exception {
		Attachment attachment1 = new Attachment();
		Attachment attachment2 = new Attachment();
		assertThat(attachment1.hashCode(), equalTo(attachment2.hashCode()));
	}

}
