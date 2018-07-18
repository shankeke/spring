package com.jusfoun.client;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.security.RawGrantedToken;

/**
 * 描述 : 接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月21日 下午1:02:36
 */
public abstract class BaseClient<T> {
	protected RestTemplate restTemplate;
	protected HttpHeaders headers;
	protected HttpEntity<Object> request;
	protected ResponseEntity<Object> response;

	protected String access_token;
	protected String refresh_token;

	protected final String BASE_URL = "http://localhost:8080/aobo";
	protected final String username = "admin";
	protected final String password = "123456";
	protected final String clientId = "web_client";
	protected final String clientSecret = "123456";

	protected T t;

	@SuppressWarnings("rawtypes")
	private RawGrantedToken token(String username, String password, String clientId, String clientSecret) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
		JSONObject t = new JSONObject();
		t.put("username", username);
		t.put("password", password);
		HttpEntity<Object> request = new HttpEntity<Object>(t, headers);
		String url = BASE_URL + "/auth/token";
		ResponseEntity<BaseResponse> exchange = restTemplate.exchange(url, HttpMethod.POST, request, BaseResponse.class);

		// 输出token请求信息
		System.out.println("\n\n>>==METHOD [/auth/token] 请求开始 =====================>");
		System.out.println("Request URL: " + url);
		System.out.println("Request Headers: " + JSON.toJSON(headers));
		System.out.println("Request Body: " + JSON.toJSON(request.getBody()));
		System.out.println("Response Headers: " + JSON.toJSON(exchange.getHeaders()));
		System.out.println("Response Body: " + JSON.toJSON(exchange.getBody()));
		System.out.println(">>==METHOD [/auth/token] 请求结束 =====================>\n\n");

		BaseResponse<?> body = exchange.getBody();
		if (body.ok()) {
			return JSON.parseObject(JSON.toJSONString(body.getContent()), RawGrantedToken.class);
		}
		return null;
	}

	/**
	 * 描述 : 初始化. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月21日 上午9:41:24
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Before
	public void init() {
		try {
			restTemplate = new RestTemplate();

			// 获取token信息
			RawGrantedToken token = token(username, password, clientId, clientSecret);
			access_token = token.getAccess_token();
			refresh_token = token.getRefresh_token();

			// 初始化接口请求信息
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
			headers.add("Authorization", "bearer " + access_token);

			// 得到model的类型信息
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];
			// 通过反射生成model的实例
			t = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 描述 :销毁方法. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月21日 上午9:41:00
	 */
	@After
	public void destroy() {
		// 控制台打印响应报文
		request = null;
		response = null;
	}

	/**
	 * 描述 :执行请求. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月21日 上午9:40:28
	 * @param method
	 *            请求方法名称
	 * @param t
	 *            参数包装实体，其实就是一个可转化为json的对象，里面是请求的参数列表:Map,Json,Entity均可
	 */
	protected void excute(String method, Object t) {
		excute(headers, method, t);
	}

	protected void excute(String method, Object t, String authorization) {
		if (StringUtils.isNotEmpty(authorization)) {
			headers.add("Authorization", authorization);
		}
		excute(headers, method, t);
	}

	protected void excute(HttpHeaders headers, String method, Object t) {
		if (t == null) {
			t = new JSONObject();
		}
		request = new HttpEntity<Object>(t, headers);
		String url = BASE_URL + method;
		response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
		System.out.println("\n\n>>==METHOD [" + method + "] 请求开始 =====================>");
		System.out.println("Request URL: " + url);
		System.out.println("Request Headers: " + JSON.toJSON(headers));
		System.out.println("Request Body: " + JSON.toJSON(request.getBody()));
		System.out.println("Response Headers: " + JSON.toJSON(response.getHeaders()));
		System.out.println("Response Body: " + JSON.toJSON(response.getBody()));
		System.out.println(">>==METHOD [" + method + "] 请求结束 =====================>\n\n");
	}

	@Test
	public void save() {
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void list() {
	}

	@Test
	public void info() {
	}

}
