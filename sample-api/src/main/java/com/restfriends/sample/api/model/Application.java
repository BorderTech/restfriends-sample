package com.restfriends.sample.api.model;

import com.restfriends.sample.api.model.types.LevelType;
import com.restfriends.sample.api.model.types.StatusType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Application details.
 */
public class Application implements Serializable {

	private String applicationId;
	private LevelType level;
	private StatusType status;
	private String firstName;
	private String lastName;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	private List<Attachment> attachments = new ArrayList<>();

	protected Application() {
		this.applicationId = null;
	}

	public Application(final String applicationId) {
		if (applicationId == null) {
			throw new IllegalArgumentException("A key must be provided.");
		}
		this.applicationId = applicationId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(final String applicationId) {
		this.applicationId = applicationId;
	}

	public LevelType getLevel() {
		return level;
	}

	public void setLevel(final LevelType level) {
		this.level = level;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(final StatusType status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(final Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(final Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(final List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.applicationId);
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
		final Application other = (Application) obj;
		return Objects.equals(this.applicationId, other.applicationId);
	}
}
