package com.restfriends.sample.restassured.test.load;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.junit.Ignore;
import org.junit.Test;

@TargetEnv("app-env.properties")
@Ignore
public class SimpleApiTest {

	@Test
	@JsonTestCase("tests/test-retrieve-application-ok.json")
	public void testRetrieveApplicationOK() {
	}

}
