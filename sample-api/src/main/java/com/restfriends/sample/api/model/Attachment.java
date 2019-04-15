package com.restfriends.sample.api.model;

import com.restfriends.sample.api.model.types.DocumentType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Attachment details.
 */
public class Attachment implements Serializable {

	private final String applicationId;
	private final String attachmentKey;
	private String fileName;
	private Long fileSize;
	private DocumentType documentType;
	private String description;
	private Date savedTimestamp;

	protected Attachment() {
		this(null, null);
	}

	public Attachment(final String applicationId, final String attachmentKey) {
		this.applicationId = applicationId;
		this.attachmentKey = attachmentKey;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public String getAttachmentKey() {
		return attachmentKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(final Long fileSize) {
		this.fileSize = fileSize;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(final DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getSavedTimestamp() {
		return savedTimestamp;
	}

	public void setSavedTimestamp(final Date savedTimestamp) {
		this.savedTimestamp = savedTimestamp;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + Objects.hashCode(this.attachmentKey);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Attachment other = (Attachment) obj;
		return Objects.equals(this.attachmentKey, other.attachmentKey);
	}
}
