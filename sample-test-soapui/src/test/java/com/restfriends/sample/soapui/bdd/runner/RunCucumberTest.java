package com.restfriends.sample.soapui.bdd.runner;

import com.github.bordertech.config.Config;
import com.github.bordertech.lde.api.LdeLauncher;
import com.restfriends.sample.api.mock.MockDataUtil;
import com.restfriends.sample.soapui.util.SoapUiUtil;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Run cucumber tests.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "classpath:api/bdd/features",
		glue = {"com.restfriends.sample.soapui.bdd.steps"}
)
public class RunCucumberTest {

	private static final String JSON_URL = "target/swagger-json/swagger.json";

	@BeforeClass
	public static void startTomcat() throws Exception {
		// Clean data
		MockDataUtil.resetData();
		// Launch LOCAL Test server
		LdeLauncher.launchServer(false);
		// Check override
		String baseUrl = Config.getInstance().getString("av.test.api.base.url", LdeLauncher.getProvider().getBaseUrl());
		// Setup SOAPUI Project
		SoapUiUtil.initProject(baseUrl, JSON_URL);
	}

	@AfterClass
	public static void closeTomcat() throws Exception {
		MockDataUtil.resetData();
		LdeLauncher.stopServer();
		// Save SOAPUI project
		SoapUiUtil.saveAsProject("target/AvRestProjectBDD.xml");
		SoapUiUtil.resetProject();
	}

}
