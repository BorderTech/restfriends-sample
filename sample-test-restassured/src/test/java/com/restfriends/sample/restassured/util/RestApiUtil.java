package com.restfriends.sample.restassured.util;

import com.github.bordertech.lde.api.LdeLauncher;
import com.github.bordertech.swagger.servlet.SwaggerPathConfig;
import com.restfriends.sample.restassured.client.v1.api.SampleServiceApi;
import com.restfriends.sample.restassured.client.v1.invoker.ApiClient;
import com.restfriends.sample.restassured.client.v1.invoker.GsonObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ErrorLoggingFilter;

/**
 * RestAssured API helper.
 */
public class RestApiUtil {

	/**
	 * Private constructor.
	 */
	private RestApiUtil() {
	}

	/**
	 * @return the attachment viewer API
	 */
	public static SampleServiceApi getApi() {
		return getApi(getBaseUrl());
	}

	/**
	 * @param baseUrl the base URL
	 * @return the rest assured API
	 */
	public static SampleServiceApi getApi(final String baseUrl) {
		return ApiClient.api(ApiClient.Config.apiConfig().reqSpecSupplier(
				() -> new RequestSpecBuilder().setConfig(getConfig())
						.addFilter(new ErrorLoggingFilter())
						.setBaseUri(baseUrl))
		).sampleService();
	}

	/**
	 * @return the default base URL for the API
	 */
	public static String getBaseUrl() {
		return LdeLauncher.getProvider().getBaseUrl() + "/" + SwaggerPathConfig.getApiPath();
	}

	/**
	 * Use GSON for JSON.
	 *
	 * @return the config
	 */
	public static RestAssuredConfig getConfig() {
		return RestAssured.config().objectMapperConfig(objectMapperConfig().defaultObjectMapper(GsonObjectMapper.gson()));
	}

}
