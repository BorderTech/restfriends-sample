package com.restfriends.sample.api.rest.v1.model;

import java.io.Serializable;

/**
 * Image request body.
 */
public class ImageRequestDetails implements Serializable {

	private byte[] image;

	public ImageRequestDetails() {
		this(null);
	}

	public ImageRequestDetails(final byte[] image) {
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(final byte[] image) {
		this.image = image;
	}

}
