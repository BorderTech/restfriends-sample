package com.restfriends.sample.api.rest.v1;

import com.github.bordertech.restfriends.envelope.ErrorResponse;
import com.restfriends.sample.api.exception.SampleBusinessException;
import com.restfriends.sample.api.model.response.ApplicationResponse;
import com.restfriends.sample.api.model.response.AttachmentContentResponse;
import com.restfriends.sample.api.model.response.AttachmentResponse;
import com.restfriends.sample.api.rest.v1.model.DocumentRequestDetails;
import com.restfriends.sample.api.rest.v1.model.ImageRequestDetails;
import com.restfriends.sample.api.rest.v1.model.StatusRequestDetails;
import com.restfriends.sample.api.service.AdminService;
import com.restfriends.sample.api.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Sample REST Resource.
 * <p>
 * The REST interface does provide new methods for the actions that require multiple values passed in the body. These values are passed as an object
 * instead of individual values. These methods can then call the original API method.
 * </p>
 */
@Api(value = "SampleService")
@Path(value = "v1")
public interface SampleResource extends AdminService, ApplicationService {

	@GET
	@Path("app/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve application via ID")
	@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class)
		,
		@ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
	})
	@Override
	public ApplicationResponse retrieveApplication(@ApiParam(value = "Application ID") @PathParam("id") final String applicationId)
			throws SampleBusinessException;

	@GET
	@Path("app/{id}/attachment/{aid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve attachment content via Application ID")
	@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class)
		,
		@ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
	})
	@Override
	public AttachmentContentResponse retrieveAttachmentContent(@PathParam("id") final String applicationId, @PathParam("aid") final String attachmentId)
			throws SampleBusinessException;

	@PUT
	@Path("app/{id}/updateStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update application status")
	@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class)
		,
		@ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
	})
	default ApplicationResponse updateApplicationStatus(@PathParam("id") final String applicationId,
			@ApiParam(name = "requestBody", required = true) final StatusRequestDetails requestBody)
			throws SampleBusinessException {
		return updateApplicationStatus(applicationId, requestBody.getStatus());
	}

	@PUT
	@Path("app/{id}/attachment/{aid}/updateDocumentType")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update document type")
	@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class)
		,
		@ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
	})
	default AttachmentResponse updateDocumentType(@PathParam("id") final String applicationId, @PathParam("aid") final String attachmentId,
			@ApiParam(name = "requestBody", required = true)
			final DocumentRequestDetails requestBody)
			throws SampleBusinessException {
		return updateDocumentType(applicationId, attachmentId, requestBody.getDocumentType());
	}

	@PUT
	@Path("app/{id}/attachment/{aid}/saveRedactImage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Save redacted image")
	@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class)
		,
		@ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
	})
	default AttachmentResponse saveRedactImage(@PathParam("id") final String applicationId, @PathParam("aid") final String attachmentId,
			@ApiParam(name = "requestBody", required = true)
			final ImageRequestDetails requestBody) throws SampleBusinessException {
		return saveRedactImage(applicationId, attachmentId, requestBody.getImage());
	}

}
