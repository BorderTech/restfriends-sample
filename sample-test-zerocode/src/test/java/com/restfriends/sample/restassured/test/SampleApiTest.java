package com.restfriends.sample.restassured.test;

import com.github.bordertech.lde.api.LdeLauncher;
import com.restfriends.sample.api.mock.MockDataUtil;
import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@TargetEnv("app-env.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class SampleApiTest {

	@BeforeClass
	public static void startTomcat() {
		LdeLauncher.launchServer(false);
	}

	@AfterClass
	public static void closeTomcat() {
		LdeLauncher.stopServer();
	}

	@Before
	@After
	public void cleanData() {
		MockDataUtil.resetData();
	}

	@Test
	@JsonTestCase("tests/test-retrieve-application-ok.json")
	public void testRetrieveApplicationOK() {
	}

	@Test
	@JsonTestCase("tests/test-retrieve-application-error.json")
	public void testRetrieveApplicationError() {
	}

}
