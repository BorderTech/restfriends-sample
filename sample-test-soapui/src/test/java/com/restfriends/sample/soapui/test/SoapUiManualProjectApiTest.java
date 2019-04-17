package com.restfriends.sample.soapui.test;

import com.eviware.soapui.config.TestStepConfig;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequest;
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep;
import com.eviware.soapui.model.testsuite.Assertable;
import com.eviware.soapui.security.assertion.ValidHttpStatusCodesAssertion;
import com.github.bordertech.config.Config;
import com.github.bordertech.lde.api.LdeLauncher;
import com.restfriends.sample.api.mock.MockDataUtil;
import com.restfriends.sample.api.model.Application;
import com.restfriends.sample.soapui.util.AvUtil;
import com.restfriends.sample.soapui.util.SoapUiUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Create the SOAPUI project via CODE to test AV API.
 */
public class SoapUiManualProjectApiTest {

	private static final String JSON_URL = "target/swagger-json/swagger.json";

	@BeforeClass
	public static void startTomcat() throws Exception {
		// Launch LOCAL Test server
		LdeLauncher.launchServer(false);
		// Check override
		String baseUrl = Config.getInstance().getString("av.test.api.base.url", LdeLauncher.getProvider().getBaseUrl());
		// Setup SOAPUI Project
		SoapUiUtil.initProject(baseUrl, JSON_URL);
	}

	@Before
	@After
	public void cleanData() {
		MockDataUtil.resetData();
	}

	@AfterClass
	public static void closeTomcat() throws Exception {
		LdeLauncher.stopServer();
		// Save SOAPUI project
		SoapUiUtil.saveAsProject("target/AvRestProject.xml");
		SoapUiUtil.resetProject();
	}

	@Test
	public void sampleTest() throws Exception {

		// Add Test case
		WsdlTestCase testCase = SoapUiUtil.getOrCreateTestCase("Test Retrieve Application");

		// Create Test Step
		TestStepConfig testStepConfig = SoapUiUtil.createTestStep("retrieveApplication", "restTestStepOK");
		RestTestRequestStep step = (RestTestRequestStep) testCase.addTestStep(testStepConfig);
		// Set parameters
		step.setPropertyValue("id", "Request33");

		// Assertions
		ValidHttpStatusCodesAssertion statusAssertion = (ValidHttpStatusCodesAssertion) step.addAssertion(ValidHttpStatusCodesAssertion.ID);
		statusAssertion.setCodes("200");

		// Run Test
		SoapUiUtil.executeTestStep(testCase, step);

		Assert.assertEquals("Assertions are Valid", Assertable.AssertionStatus.VALID, step.getAssertionStatus());

		// Check response
		RestTestRequest req = step.getTestRequest();
		Application appl = AvUtil.extractApplication(req.getResponse());
		Assert.assertEquals("Valid ID", "Request33", appl.getApplicationId());
	}

}
