package com.restfriends.sample.api.model;

import com.restfriends.sample.api.model.types.LevelType;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests for {@link Application} POJO.
 */
public class ApplicationTest {

	@Test
	public void testConstructor1() {
		Application appl = new Application("X");
		assertEquals("Constructor1 - key does not match", "X", appl.getApplicationId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor1Invalid() {
		new Application(null);
	}

	@Test
	public void testConstructor2() {
		Application appl = new Application();
		assertThat(appl.getApplicationId(), nullValue());
	}

	@Test
	public void testFields() {
		Application appl = new Application("X");
		appl.setLevel(LevelType.CASUAL);
		Attachment dummyAttahcment = new Attachment();
		appl.setAttachments(Arrays.asList(dummyAttahcment));
		assertEquals("Constructor1 - key does not match", "X", appl.getApplicationId());
		assertThat(appl.getLevel(), equalTo(LevelType.CASUAL));
		assertThat(appl.getAttachments().get(0), equalTo(dummyAttahcment));
	}

	@Test
	public void equalsNull() throws Exception {
		Application application = new Application();
		assertThat(application.equals(null), equalTo(false));
	}

	@Test
	public void equalsEquals() throws Exception {
		Application application = new Application();
		assertThat(application.equals(application), equalTo(true));
	}

	@Test
	public void equalsWrongInstance() throws Exception {
		Application application = new Application();
		Object object = new Object();
		assertThat(application.equals(object), equalTo(false));
	}

	@Test
	public void equalsSameData() throws Exception {
		Application application1 = new Application("id");
		Application application2 = new Application("id");
		assertThat(application1.equals(application2), equalTo(true));
	}

	@Test
	public void testHashCode() throws Exception {
		Application application1 = new Application("id");
		Application application2 = new Application("id");
		assertThat(application1.hashCode(), equalTo(application2.hashCode()));
	}
}
