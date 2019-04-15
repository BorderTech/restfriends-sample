package com.restfriends.sample.api.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Attachment content.
 */
public class AttachmentContent implements Serializable {

	private String key;
	private byte[] bytes;
	private String fileName;
	private String mimeType;

	protected AttachmentContent() {
		this(null, null, null, null);
	}

	public AttachmentContent(final String key, final byte[] bytes, final String fileName, final String mimeType) {
		this.key = key;
		this.bytes = bytes;
		this.fileName = fileName;
		this.mimeType = mimeType;
	}

	public String getKey() {
		return key;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public String getFileName() {
		return fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setBytes(final byte[] bytes) {
		this.bytes = bytes;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + Objects.hashCode(this.key);
		return hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AttachmentContent other = (AttachmentContent) obj;
		return Objects.equals(this.key, other.key);
	}

}
