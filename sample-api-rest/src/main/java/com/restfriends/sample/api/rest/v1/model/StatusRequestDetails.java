package com.restfriends.sample.api.rest.v1.model;

import com.restfriends.sample.api.model.types.StatusType;
import java.io.Serializable;

/**
 * Status request body.
 */
public class StatusRequestDetails implements Serializable {

	private StatusType status;

	public StatusRequestDetails() {
		this(null);
	}

	public StatusRequestDetails(final StatusType status) {
		this.status = status;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(final StatusType status) {
		this.status = status;
	}

}
