package com.restfriends.sample.restassured.bdd.runner;

import com.github.bordertech.lde.api.LdeLauncher;
import com.restfriends.sample.api.mock.MockDataUtil;
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
		glue = {"com.restfriends.sample.restassured.bdd.steps"}
)
public class RunCucumberTest {

	@BeforeClass
	public static void startTomcat() {
		MockDataUtil.resetData();
		LdeLauncher.launchServer(false);
	}

	@AfterClass
	public static void closeTomcat() {
		MockDataUtil.resetData();
		LdeLauncher.stopServer();
	}
}
