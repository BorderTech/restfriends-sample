package com.restfriends.sample.api.model.types;

/**
 * Application status type.
 */
public enum StatusType {
	SUBMITTED("Submitted"),
	APPROVED("Approved"),
	REJECTED("Rejected"),
	WITHDRAWN("Withdrawn");

	/**
	 * @param desc the status description
	 */
	StatusType(final String desc) {
		this.desc = desc;
	}
	String desc;

	/**
	 * @return the status description
	 */
	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return desc;
	}

}
