package com.restfriends.sample.soapui.util;

import com.restfriends.sample.api.model.Application;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.eviware.soapui.impl.wsdl.submit.transports.http.HttpResponse;

/**
 * SOAUPUI project helper.
 */
public class AvUtil {

//    private static final Logger LOGGER = Logger.get(AvUtil.class);
	/**
	 * Extract the application from the response.
	 *
	 * @param response the test response
	 * @return the application
	 */
	public static Application extractApplication(final HttpResponse response) {
		if (response.getStatusCode() != 200) {
			throw new IllegalStateException("Invalid response to extract application");
		}
		ApplicationResponse resp = SoapUiUtil.handleJSONToPojo(response.getContentAsString(), ApplicationResponse.class);
		return resp.getData();
	}

}
