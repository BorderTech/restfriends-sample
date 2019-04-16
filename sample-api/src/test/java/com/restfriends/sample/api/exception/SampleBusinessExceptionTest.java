package com.restfriends.sample.api.exception;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class SampleBusinessExceptionTest {

	@Test
	public void testConstructor1() throws Exception {
		SampleBusinessException sampleException = new SampleBusinessException("message");
		assertThat(sampleException.getMessage(), equalTo("message"));
		assertThat(sampleException.getCause(), nullValue());
	}

	@Test
	public void testConstructor2() throws Exception {
		Exception ex = new Exception();
		SampleBusinessException sampleException = new SampleBusinessException("message", ex);
		assertThat(sampleException.getMessage(), equalTo("message"));
		assertThat(sampleException.getCause(), sameInstance(ex));
	}

}
