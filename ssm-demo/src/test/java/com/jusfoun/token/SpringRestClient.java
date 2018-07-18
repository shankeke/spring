package com.jusfoun.token;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.jusfoun.client.BaseClient;

public class SpringRestClient extends BaseClient<Object> {

	public static final String REST_SERVICE_URI = "http://localhost:8080/aobo/";

	public static final String AUTH_SERVER_URI = "http://localhost:8080/aobo/oauth/token";

	public static final String QPM_PASSWORD_GRANT = "?grant_type=password&username=admin&password=123456";
	public static final String QPM_REFRESH_TOKEN_GRANT = "?grant_type=refresh_token&refresh_token=e147fef0-f850-4f38-8bae-9465eaa44384";
	public static final String QPM_ACCESS_TOKEN = "?access_token=";

	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return headers;
	}

	private static HttpHeaders getHeaders(String access_token) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "bearer " + access_token);
		return headers;
	}

	private static HttpHeaders getHeadersWithClientCredentials() {
		// String plainClientCredentials = "my-trusted-client:123456";
		// String base64ClientCredentials = new
		// String(Base64.encodeBase64(plainClientCredentials.getBytes()));
		HttpHeaders headers = getHeaders();
		// headers.add("Authorization", "Basic " + base64ClientCredentials);
		headers.add("Authorization", "Basic bXktdHJ1c3RlZC1jbGllbnQ6MTIzNDU2");
		return headers;
	}

	@Test
	public void token() {
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
		requestEntity.add("grant_type", "password");
		requestEntity.add("username", "admin");
		requestEntity.add("password", "123456");
		request = new HttpEntity<Object>(requestEntity, getHeadersWithClientCredentials());
		response = restTemplate.exchange(AUTH_SERVER_URI, HttpMethod.POST, request, Object.class);
		System.out.println(JSON.toJSON(request.getHeaders()));
		System.out.println(JSON.toJSON(request.getBody()));
		System.out.println(JSON.toJSON(response.getHeaders()));
		System.out.println(JSON.toJSON(response.getBody()));
	}

	@Test
	public void token1() {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI + QPM_PASSWORD_GRANT, HttpMethod.POST,
				request, Object.class);
		System.out.println(JSON.toJSON(request.getHeaders()));
		System.out.println(JSON.toJSON(request.getBody()));
		System.out.println(JSON.toJSON(response.getHeaders()));
		System.out.println(JSON.toJSON(response.getBody()));
	}

	@Test
	public void refresh_token() {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI + QPM_REFRESH_TOKEN_GRANT,
				HttpMethod.POST, request, Object.class);
		System.out.println(JSON.toJSON(request.getHeaders()));
		System.out.println(JSON.toJSON(request.getBody()));
		System.out.println(JSON.toJSON(response.getHeaders()));
		System.out.println(JSON.toJSON(response.getBody()));
	}

	@Test
	public void user() {
		String access_token = "e58e848f-9225-44d7-828c-e37e3ad9594d";
		Assert.notNull(access_token, "Token is null ......");
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(getHeaders(access_token));
		ResponseEntity<Object> response = restTemplate.exchange(REST_SERVICE_URI + "/user", HttpMethod.POST, request,
				Object.class);
		System.out.println(response.getBody().toString());
	}

}