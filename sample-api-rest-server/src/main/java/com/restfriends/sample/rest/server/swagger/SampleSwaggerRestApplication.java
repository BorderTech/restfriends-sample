package com.restfriends.sample.rest.server.swagger;

import com.github.bordertech.swagger.application.SwaggerRestApplication;
import com.github.bordertech.swagger.servlet.JacksonObjectMapperProvider;
import com.restfriends.sample.rest.server.resource.v1.impl.SampleResourceImpl;

/**
 * Sample REST Swagger/Jersey Application.
 */
public class SampleSwaggerRestApplication extends SwaggerRestApplication {

	/**
	 * Default constructor.
	 */
	public SampleSwaggerRestApplication() {
		super(SampleResourceImpl.class, JacksonObjectMapperProvider.class);
	}
}
