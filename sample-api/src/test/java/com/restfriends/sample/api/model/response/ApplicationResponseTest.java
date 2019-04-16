package com.restfriends.sample.api.model.response;

import com.restfriends.sample.api.model.Application;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class ApplicationResponseTest {

	@Test
	public void constructorTest1() {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		assertThat(applicationResponse.getData(), nullValue());
	}

	@Test
	public void constructorTest2() {
		Application application = new Application("appId");
		ApplicationResponse applicationResponse = new ApplicationResponse(application);
		assertThat(applicationResponse.getData(), sameInstance(application));
	}

}
