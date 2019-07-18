package com.restfriends.sample.rest.server.swagger;

import com.github.bordertech.swagger.servlet.SwaggerJersey2Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Swagger jersey servlet.
 */
@WebServlet(urlPatterns = "/api/*",
		initParams
		= {
			// Tell Jersey to use JSON
			@WebInitParam(name = "com.sun.jersey.api.json.POJOMappingFeature", value = "true")
			,
            // Tell Jersey the Application
            @WebInitParam(name = "javax.ws.rs.Application", value = "com.restfriends.sample.rest.server.swagger.SampleSwaggerRestApplication")
		},
		loadOnStartup = 1
)
public class SampleSwaggerJerseyServlet extends SwaggerJersey2Servlet {
}
