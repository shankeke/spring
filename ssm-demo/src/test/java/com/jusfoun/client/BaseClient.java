package com.jusfoun.client;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.common.utils.entry.EntityUtils;
import com.jusfoun.security.RawGrantedToken;

/**
 * 描述 : 接口测试基类，测试接口时可继承该类并可实现没有实现的方法，或者自己扩展方法，调用封装好的方法直接用于发送接口测试请求<br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月21日 下午1:02:36
 */
public abstract class BaseClient<T> {

	protected RestTemplate restTemplate;
	protected HttpHeaders headers;
	protected HttpEntity<Object> request;
	protected ResponseEntity<Object> response;

	protected T t;

	protected String access_token;
	protected String refresh_token;

	protected final String BASE_URL = "http://localhost:8080/aobo%s";
	protected final String username = "admin";
	protected final String password = "123456";
	protected final String clientId = "web_client";
	protected final String clientSecret = "123456";

	@SuppressWarnings("rawtypes")
	private RawGrantedToken token(String username, String password, String clientId, String clientSecret) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
		JSONObject t = new JSONObject();
		t.put("username", username);
		t.put("password", password);
		HttpEntity<Object> request = new HttpEntity<Object>(t, headers);
		String url = String.format(BASE_URL, "/auth/token");
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
			// 初始化接口请求信息
			headers = new HttpHeaders();

			// 获取token信息
			RawGrantedToken token = token(username, password, clientId, clientSecret);
			access_token = token.getAccess_token();
			refresh_token = token.getRefresh_token();

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
	 * 描述: 请求参数是json格式时使用该方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月19日 上午10:09:18
	 * @param method
	 *            请求子地址
	 * @param t
	 *            参数载体，可以使Map映射或者其他javaBean实体
	 * @throws Exception
	 */
	protected void rest(String method, Object t) {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		excute(headers, method, t, RequestType.REST);
	}

	/**
	 * 描述: get请求时使用该方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月19日 上午10:09:18
	 * @param method
	 *            请求子地址
	 * @param t
	 *            参数载体，可以使Map映射或者其他javaBean实体
	 * @throws Exception
	 */
	protected void get(String method, Object t) {
		excute(headers, method, t, RequestType.GET);
	}

	/**
	 * 描述: post请求时使用该方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月19日 上午10:09:18
	 * @param method
	 *            请求子地址
	 * @param t
	 *            参数载体，可以使Map映射或者其他javaBean实体
	 * @throws Exception
	 */
	protected void post(String method, Object t) {
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		excute(headers, method, t, RequestType.POST);
	}

	protected void excute(HttpHeaders headers, String method, Object t, RequestType requestType) {
		if (t == null) {
			t = new HashMap<String, Object>();
		}
		// 拼接请求地址
		String url = String.format(BASE_URL, method);
		try {
			// 判断请求的方式并设置不同的参数形式请求接口
			switch (requestType) {
				case GET :
					StringBuffer buf = new StringBuffer();
					buf.append(url).append("?").append(createLinkStringByGet(t));
					url = buf.toString();
					request = new HttpEntity<Object>(t, headers);// 这行代码本身没什么用处，只是打印参数方便
					response = restTemplate.getForEntity(url, Object.class);
					break;
				case POST :
					request = new HttpEntity<Object>(createLinkStringByPost(t), headers);
					response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
					break;
				default :
					request = new HttpEntity<Object>(t, headers);
					response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
					break;
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n\n>>==METHOD [" + method + "] 请求开始 =====================>");
		System.out.println("Request URL: " + url);
		System.out.println("Request Headers: " + JSON.toJSON(headers));
		System.out.println("Request Body: " + JSON.toJSON(request.getBody()));
		System.out.println("Response Headers: " + JSON.toJSON(response.getHeaders()));
		System.out.println("Response Body: " + JSON.toJSON(response.getBody()));
		System.out.println(">>==METHOD [" + method + "] 请求结束 =====================>\n\n");
	}

	/**
	 * 描述: 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月19日 上午9:02:30
	 * @param t
	 *            需要排序并参与字符拼接的参数载体
	 * @return 拼接后字符串
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private String createLinkStringByGet(Object t) throws Exception {
		Map<String, Object> params = null;
		if (t != null) {
			if (Map.class.isAssignableFrom(t.getClass())) {
				params = (Map<String, Object>) t;
			} else {
				params = EntityUtils.transBean2Map(t);
			}

			// 拼接参数
			final StringBuffer buf = new StringBuffer();
			params.forEach((k, v) -> {
				try {
					v = URLEncoder.encode(v.toString(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				buf.append(k).append("=").append(v).append("&");
			});

			// 去掉最后面的“&”并返回
			if (StringUtils.isNotEmpty(buf)) {
				return StringUtils.stripEnd(buf.toString(), "&");
			}
		}
		return null;
	}

	/**
	 * 描述: 把参数转化成map格式，用于表单参数. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月19日 上午9:02:30
	 * @param t
	 *            参数载体
	 * @return 参数映射
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private MultiValueMap<String, Object> createLinkStringByPost(Object t) throws Exception {
		if (t != null) {
			// 如果穿的参数就是Map类型这不转变，否者需要把java bean 转换为Map类型
			Map<String, Object> map = null;
			if (Map.class.isAssignableFrom(t.getClass())) {
				map = (Map<String, Object>) t;
			} else {
				map = EntityUtils.transBean2Map(t);
			}

			// 把参数装载到params中
			// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
			final MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			map.forEach((k, v) -> {
				params.add(k, v);
			});
			return params;
		}
		return null;
	}

	public enum RequestType {
		GET, POST, REST;
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
