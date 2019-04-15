package com.restfriends.sample.api.rest.v1.model;

import com.restfriends.sample.api.model.types.DocumentType;
import java.io.Serializable;

/**
 * Update document request body.
 */
public class DocumentRequestDetails implements Serializable {

	private DocumentType documentType;

	public DocumentRequestDetails() {
		this(null);
	}

	public DocumentRequestDetails(final DocumentType status) {
		this.documentType = status;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(final DocumentType documentType) {
		this.documentType = documentType;
	}

}
