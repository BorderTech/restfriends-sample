package com.restfriends.sample.restassured.test.load;

import com.github.bordertech.lde.api.LdeLauncher;
import com.restfriends.sample.api.mock.MockDataUtil;
import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeLoadRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Load test retrieve Application.
 */
@LoadWith("load_config_sample.properties")
@TestMapping(testClass = SimpleApiTest.class, testMethod = "testRetrieveApplicationOK")
@RunWith(ZeroCodeLoadRunner.class)
public class SimpleGetLoadTest {

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
