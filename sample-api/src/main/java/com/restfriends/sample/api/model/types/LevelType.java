package com.restfriends.sample.api.model.types;

/**
 * Application membership level type.
 */
public enum LevelType {
	CASUAL("Casual member"),
	GOLD("Gold member"),
	PLATINUM("Platinum member");

	/**
	 * @param desc the level description
	 */
	LevelType(final String desc) {
		this.desc = desc;
	}
	String desc;

	/**
	 * @return the level description
	 */
	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return desc;
	}

}
