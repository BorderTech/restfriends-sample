package com.restfriends.sample.soapui.test;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import com.github.bordertech.config.Config;
import com.github.bordertech.lde.api.LdeLauncher;
import com.restfriends.sample.api.mock.MockDataUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Use a SOAPUI project created in the SOAPUI IDE to test AV API.
 */
public class SoapUiIdeProjectApiTest {

	@BeforeClass
	public static void startTomcat() throws Exception {
		// Launch LOCAL Test server
		LdeLauncher.launchServer(false);
	}

	@Before
	@After
	public void cleanData() {
		MockDataUtil.resetData();
	}

	@AfterClass
	public static void closeTomcat() throws Exception {
		LdeLauncher.stopServer();
	}

	@Test
	public void runSoapProject() throws Exception {
		// Check override
		String baseUrl = Config.getInstance().getString("av.test.api.base.url", LdeLauncher.getProvider().getBaseUrl());
		// Run SOAPUI project
		SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
		runner.setProjectFile("SampleRestProject.xml");
		runner.setEndpoint(baseUrl);
		runner.setJUnitReport(true);
		runner.setPrintReport(true);
		runner.setOutputFolder("target/soapui");
		runner.run();
	}

}
