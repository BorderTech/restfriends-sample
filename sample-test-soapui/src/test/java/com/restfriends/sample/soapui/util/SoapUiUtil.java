package com.restfriends.sample.soapui.util;

import com.eviware.soapui.config.TestStepConfig;
import com.eviware.soapui.impl.rest.RestMethod;
import com.eviware.soapui.impl.rest.RestRequest;
import com.eviware.soapui.impl.rest.RestResource;
import com.eviware.soapui.impl.rest.RestService;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.submit.transports.http.HttpResponse;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep;
import com.eviware.soapui.impl.wsdl.teststeps.registry.RestRequestStepFactory;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.support.types.StringToObjectMap;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bordertech.restfriends.envelope.ErrorDetail;
import com.github.bordertech.restfriends.envelope.impl.ErrorResponse;
import com.smartbear.swagger.Swagger2Importer;
import java.io.IOException;
import javax.json.stream.JsonGenerationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlException;

/**
 * SOAUPUI project helper.
 */
public class SoapUiUtil {

//	private static final Logger LOGGER = Logger.get(SoapUiUtil.class);
	private static WsdlProject PROJECT;
	private static WsdlTestSuite SUITE;
	private static RestService SERVICE;

	/**
	 * Setup the SOAP UI Project.
	 *
	 * @param baseUrl the REST end point
	 * @param swaggerUrl the swagger JSON URL location
	 *
	 */
	public static void initProject(final String baseUrl, final String swaggerUrl) {
		if (StringUtils.isBlank(baseUrl)) {
			throw new IllegalArgumentException("Base URL must be provided.");
		}
		if (StringUtils.isBlank(swaggerUrl)) {
			throw new IllegalArgumentException("Swagger URL must be provided.");
		}

		if (PROJECT != null) {
			throw new IllegalArgumentException("SOAP UI Project already initialised. Make sure resetProject is called.");
		}
		// Setup SOAPUI Project
		try {
			PROJECT = new WsdlProject();
		} catch (IOException | SoapUIException | XmlException e) {
			throw new IllegalStateException("Could not create SOAP UI project. " + e.getMessage(), e);
		}
		PROJECT.setName("swagger_rest_test");

		// Setup SUITE
		SUITE = PROJECT.addNewTestSuite("RestTestSuite");

		// Import Swagger
		Swagger2Importer importer = new Swagger2Importer(PROJECT);
		RestService[] svcs = importer.importSwagger(swaggerUrl);

		// Check a Service is defined
		if (svcs == null || svcs.length == 0) {
			throw new IllegalStateException("No service defined in " + swaggerUrl);
		}
		if (svcs.length > 1) {
			throw new IllegalStateException("More than one service defined in " + swaggerUrl);
		}
		SERVICE = svcs[0];
		SERVICE.addEndpoint(baseUrl);
	}

	/**
	 * Save the project so it can be used in the SOAP UI IDE.
	 *
	 * @param filename the target file name
	 */
	public static void saveAsProject(final String filename) {
		try {
			PROJECT.saveAs(filename);
		} catch (IOException e) {
			throw new IllegalStateException("Could not save SOAP UI project. " + e.getMessage(), e);
		}
	}

	/**
	 * Reset the project details.
	 */
	public static void resetProject() {
		PROJECT = null;
		SUITE = null;
		SERVICE = null;
	}

	/**
	 * Get or create the test case name in the test suite.
	 *
	 * @param testCaseName the test case name
	 * @return the test case instance
	 */
	public static WsdlTestCase getOrCreateTestCase(final String testCaseName) {
		WsdlTestCase testCase = SUITE.getTestCaseByName(testCaseName);
		if (testCase != null) {
			return testCase;
		}
		return SUITE.addNewTestCase(testCaseName);
	}

	/**
	 * Create a test step for a REST method.
	 *
	 * @param method the rest method name
	 * @param name the name of the test step
	 * @return the rest test step
	 */
	public static TestStepConfig createTestStep(final String method, final String name) {
		RestMethod restMethod = getRestMethodByName(method);
		RestRequest restRequest = restMethod.addNewRequest(name);
		TestStepConfig config = RestRequestStepFactory.createConfig(restRequest, name);
		return config;
	}

	/**
	 * Execute the test step.
	 *
	 * @param testCase the test case
	 * @param restTestStep the step to run
	 * @return the test step result
	 */
	public static TestStepResult executeTestStep(final WsdlTestCase testCase, final RestTestRequestStep restTestStep) {
		WsdlTestCaseRunner runner = new WsdlTestCaseRunner(testCase, new StringToObjectMap());
		TestStepResult result = runner.runTestStep(restTestStep);
		printTestStepResult(result);
		return result;
	}

	/**
	 *
	 * @param <T> the POJO class type
	 * @param json the JSON string representation of the class
	 * @param clazz the POJO class type
	 * @return the POJO instance
	 */
	public static <T> T handleJSONToPojo(final String json, final Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			T pojo = mapper.readValue(json, clazz);
			return pojo;
		} catch (JsonGenerationException | IOException e) {
			throw new IllegalStateException("Could not convert JSON response. " + e.getMessage(), e);
		}
	}

	/**
	 * Extract the error details from the response.
	 *
	 * @param response the test response
	 * @return the error detail
	 */
	public static ErrorDetail extractErrorDetail(final HttpResponse response) {
		if (response.getStatusCode() == 200) {
			throw new IllegalStateException("Invalid response to extract error details");
		}
		ErrorResponse resp = handleJSONToPojo(response.getContentAsString(), ErrorResponse.class);
		return resp.getError();
	}

	private static void printTestStepResult(final TestStepResult testStepResult) {
//		LOGGER.debug("Result: " + testStepResult.getStatus());
//		LOGGER.debug("Time Taken: " + testStepResult.getTimeTaken());
//		LOGGER.debug("Request: " + ((MessageExchange) testStepResult).getRequestContent());
//		LOGGER.debug("Response: " + ((MessageExchange) testStepResult).getResponseContent());
//
//		String[] results = testStepResult.getMessages();
//		for (String result : results) {
//			LOGGER.debug("Result: " + result);
//		}
	}

	private static RestMethod getRestMethodByName(final String methodName) {
		for (RestResource resource : SERVICE.getAllResources()) {
			RestMethod method = resource.getRestMethodByName(methodName);
			if (method != null) {
				return method;
			}
		}
		throw new IllegalStateException("RestMethod [" + methodName + "] not defined in swagger.");
	}

}
