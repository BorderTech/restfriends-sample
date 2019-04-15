package com.restfriends.sample.restassured.test.load;

import com.restfriends.sample.restassured.client.v1.api.SampleServiceApi;
import com.restfriends.sample.restassured.client.v1.invoker.ResponseSpecBuilders;
import com.restfriends.sample.restassured.client.v1.model.Application;
import com.restfriends.sample.restassured.client.v1.model.ApplicationResponse;
import com.restfriends.sample.restassured.util.RestApiUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test used by the {@link SimpleGetLoadTest}.
 */
@Ignore
public class SimpleGetLoad {

	private final SampleServiceApi api = RestApiUtil.getApi();

	@Test
	public void retrieveApplication() {

		// Setup IDs
		String id = "TEST123";

		// Expect 200 - Successful
		ApplicationResponse resp = api.retrieveApplication()
				.idPath(id)
				.executeAs(ResponseSpecBuilders.validatedWith(ResponseSpecBuilders.shouldBeCode(200)));

		// Check response
		Application appl = resp.getData();
		Assert.assertEquals("Invalid application id returned", appl.getApplicationId(), id);
	}

}
