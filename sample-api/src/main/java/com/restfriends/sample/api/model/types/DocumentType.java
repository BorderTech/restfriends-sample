package com.restfriends.sample.api.model.types;

/**
 * Attachment type.
 */
public enum DocumentType {
	DRIVERS_LICENCE("Drivers licence"),
	UTILITY_BILL("Utility bill"),
	PASSPORT_PHOTO("Passport photo"),
	OTHER("Other");

	/**
	 * @param desc the attachment type description
	 */
	DocumentType(final String desc) {
		this.desc = desc;
	}
	String desc;

	/**
	 * @return the attachment type description
	 */
	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return desc;
	}

}
