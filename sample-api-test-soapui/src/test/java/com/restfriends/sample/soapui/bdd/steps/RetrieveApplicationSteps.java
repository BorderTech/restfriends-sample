package com.restfriends.sample.soapui.bdd.steps;

import com.eviware.soapui.config.TestStepConfig;
import com.eviware.soapui.impl.wsdl.submit.transports.http.HttpResponse;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequest;
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep;
import com.github.bordertech.restfriends.envelope.ErrorDetail;
import com.restfriends.sample.api.model.Application;
import com.restfriends.sample.soapui.util.AvUtil;
import com.restfriends.sample.soapui.util.SoapUiUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Test;

/**
 * Create SOAPUI project for the application steps.
 */
public class RetrieveApplicationSteps {

	private Application application;
	private ErrorDetail error;

	@Given("A retrieve application service is available")
	public void wantToRetrieveApplication() {
		this.error = null;
		this.application = null;
	}

	@Test
	public void sampleTest() throws Exception {

	}

	@When("User retrieves application {string}")
	public void retrieveApplication(final String id) {

		// Add Test case
		WsdlTestCase testCase = SoapUiUtil.getOrCreateTestCase("Test Retrieve Application");

		// Create Test Step
		TestStepConfig testStepConfig = SoapUiUtil.createTestStep("retrieveApplication", "restTestStepBDD-" + id);
		RestTestRequestStep step = (RestTestRequestStep) testCase.addTestStep(testStepConfig);
		// Set parameters
		step.setPropertyValue("id", id);
		// Run Test
		SoapUiUtil.executeTestStep(testCase, step);

		// Check response
		RestTestRequest req = step.getTestRequest();
		HttpResponse resp = req.getResponse();
		switch (resp.getStatusCode()) {
			// OK
			case 200:
				application = AvUtil.extractApplication(resp);
				break;
			// Error
			case 400:
			case 500:
				error = SoapUiUtil.extractErrorDetail(resp);
				break;
			default:
				throw new IllegalStateException("Unhandled status code [" + resp.getStatusCode() + "].");
		}
	}

	@Then("User gets {string} application")
	public void shouldHaveApplication(final String id) {
		Assert.assertNotNull("Application should have been retrieved", application);
		Assert.assertEquals("Incorrect applicaiton id retrieved", id, application.getApplicationId());
	}

	@Then("User gets application exception for {string}")
	public void shouldHaveApplicationException(final String id) {
		Assert.assertNotNull("Application should have created exception", error);
	}

}
