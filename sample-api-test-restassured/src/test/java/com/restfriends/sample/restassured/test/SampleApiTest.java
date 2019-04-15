package com.restfriends.sample.restassured.test;

import com.github.bordertech.lde.api.LdeLauncher;
import com.restfriends.sample.api.mock.MockDataUtil;
import com.restfriends.sample.restassured.client.v1.api.SampleServiceApi;
import com.restfriends.sample.restassured.client.v1.invoker.ResponseSpecBuilders;
import com.restfriends.sample.restassured.client.v1.model.Application;
import com.restfriends.sample.restassured.client.v1.model.ApplicationResponse;
import com.restfriends.sample.restassured.client.v1.model.Attachment;
import com.restfriends.sample.restassured.client.v1.model.AttachmentContent;
import com.restfriends.sample.restassured.client.v1.model.AttachmentContentResponse;
import com.restfriends.sample.restassured.client.v1.model.AttachmentResponse;
import com.restfriends.sample.restassured.client.v1.model.DocumentRequestDetails;
import com.restfriends.sample.restassured.client.v1.model.ImageRequestDetails;
import com.restfriends.sample.restassured.client.v1.model.StatusRequestDetails;
import com.restfriends.sample.restassured.util.RestApiUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * RestAssured API tests for AttachmentViewerApi.
 */
public class SampleApiTest {

	private SampleServiceApi api;

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

	@Before
	public void setupApi() {
		api = RestApiUtil.getApi();
	}

	@Test
	public void processUpdateApplicationStatusSuccessful() {

		// Setup IDs
		String id = "TEST123";
		StatusRequestDetails statusRequestDetails = new StatusRequestDetails();
		statusRequestDetails.setStatus(StatusRequestDetails.StatusEnum.SUBMITTED);

		// Expect 200 - Successful
		ApplicationResponse resp = api.updateApplicationStatus()
				.idPath(id)
				.body(statusRequestDetails)
				.executeAs(ResponseSpecBuilders.validatedWith(ResponseSpecBuilders.shouldBeCode(200)));

		// Check response and status updated
		Application attachment = resp.getData();
		Assert.assertEquals("Invalid application id returned", id, attachment.getApplicationId());
	}

	@Test
	public void retrieveApplicationSuccessful() {

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

	@Test
	public void retrieveAttachmentContentSuccessful() {

		// Setup IDs
		String id = "TEST123";
		String aid = "id-1";

		// Expect 200 - Successful
		AttachmentContentResponse resp = api.retrieveAttachmentContent()
				.idPath(id)
				.aidPath(aid)
				.executeAs(ResponseSpecBuilders.validatedWith(ResponseSpecBuilders.shouldBeCode(200)));

		// Check response
		AttachmentContent content = resp.getData();
		Assert.assertNotNull("Invalid content returned", content.getBytes());
	}

	@Test
	public void saveRedactImageSuccessful() {

		// Setup IDs
		String id = "TEST123";
		String aid = "id-1";
		byte[] img = "REDACTIMAGE".getBytes();
		ImageRequestDetails imageRequestDetails = new ImageRequestDetails();
		imageRequestDetails.setImage(img);

		// Expect 200 - Successful
		AttachmentResponse resp = api.saveRedactImage()
				.idPath(id)
				.aidPath(aid)
				.body(imageRequestDetails)
				.executeAs(ResponseSpecBuilders.validatedWith(ResponseSpecBuilders.shouldBeCode(200)));

		// Check response
		Attachment attachment = resp.getData();
		Assert.assertEquals("Invalid application id returned", id, attachment.getApplicationId());
		Assert.assertEquals("Invalid attachment id returned", aid, attachment.getAttachmentKey());

		// Retrieve Content to check updated
		AttachmentContentResponse contentResp = api.retrieveAttachmentContent()
				.idPath(id)
				.aidPath(aid)
				.executeAs(ResponseSpecBuilders.validatedWith(ResponseSpecBuilders.shouldBeCode(200)));
		AttachmentContent content = contentResp.getData();
		Assert.assertArrayEquals("Image content should have been updated", img, content.getBytes());
	}

	@Test
	public void updateDocumentTypeSuccessful() {

		// Setup IDs
		String id = "TEST123";
		String aid = "id-1";
		DocumentRequestDetails documentRequestDetails = new DocumentRequestDetails();
		documentRequestDetails.setDocumentType(DocumentRequestDetails.DocumentTypeEnum.OTHER);

		// Expect 200 - Successful
		AttachmentResponse resp = api.updateDocumentType()
				.idPath(id)
				.aidPath(aid)
				.body(documentRequestDetails)
				.executeAs(ResponseSpecBuilders.validatedWith(ResponseSpecBuilders.shouldBeCode(200)));

		// Check response
		Attachment attachment = resp.getData();
		Assert.assertEquals("Invalid application id returned", id, attachment.getApplicationId());
		Assert.assertEquals("Invalid attachment id returned", aid, attachment.getAttachmentKey());
	}

}
