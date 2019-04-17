package com.restfriends.sample.restassured.bdd.steps;

import com.restfriends.sample.restassured.client.v1.model.Application;
import com.restfriends.sample.restassured.client.v1.model.ApplicationResponse;
import com.restfriends.sample.restassured.client.v1.model.ErrorDetail;
import com.restfriends.sample.restassured.client.v1.model.ErrorResponse;
import com.restfriends.sample.restassured.util.RestApiUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

/**
 * Retrieve application steps using RestAssured.
 */
public class RetrieveApplicationSteps {

	private Application application;
	private ErrorDetail error;

	@Given("A retrieve application service is available")
	public void wantToRetrieveApplication() {
		RestAssured.baseURI = RestApiUtil.getBaseUrl();
		this.error = null;
		this.application = null;
	}

	@When("User retrieves application {string}")
	public void retrieveApplication(final String id) {
		Response resp = RestAssured.get("v1/app/{id}", id);
		switch (resp.getStatusCode()) {
			// OK
			case 200:
				application = resp.as(ApplicationResponse.class).getData();
				break;
			// Error
			case 400:
			case 500:
				this.error = resp.as(ErrorResponse.class).getError();
				break;
			default:
				throw new IllegalStateException("Unhandled status code [" + resp.getStatusCode() + "].");
		}
	}

	@Then("User gets {string} application")
	public void shouldHaveApplication(final String id) {
		Assert.assertNotNull("Application should have been retrieved", application);
		Assert.assertEquals("Incorrect applicaiton id retrieved", id, application.getApplicationId());
	}

	@Then("User gets application exception for {string}")
	public void shouldHaveApplicationException(final String id) {
		Assert.assertNotNull("Application should have created exception", error);
	}

}
