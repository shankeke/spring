package com.jusfoun.common.utils.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 说明： http请求. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月6日 上午11:34:50
 */
public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 默认字符集
	 */
	private static final String DEFAULT_CHARSET = "UTF-8";
	/**
	 * 默认读超时时间
	 */
	private static final int DEFAULT_READTIMEOUT = 30000;
	/**
	 * 默认链接超时时间
	 */
	private static final int DEFAULT_CONNECTTIMEOUT = 30000;

	final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};

	public static String httpsPost(String urlStr, String params, String charSet) throws Exception {
		HttpsURLConnection httpsConn = null;
		try {
			byte[] data = params.getBytes(charSet);
			URL url = new URL(urlStr);
			HttpsHandler.trustAllHttpsCertificates();
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			httpsConn = (HttpsURLConnection) url.openConnection();
			httpsConn.setRequestMethod("POST");
			httpsConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
			httpsConn.setRequestProperty("Content-Length", String.valueOf(data.length));
			httpsConn.setDoInput(true);
			httpsConn.setDoOutput(true);
			httpsConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
			httpsConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
			httpsConn.connect();
			OutputStream out = httpsConn.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			return getResponseResult(httpsConn, urlStr, charSet);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(urlStr, ex);
			throw new Exception("请求失败：" + urlStr);
		} finally {
			if (null != httpsConn) {
				httpsConn.disconnect();
			}
		}
	}

	public static String httpsPostJson(String urlStr, String params, String charSet) throws Exception {
		HttpURLConnection httpConn = null;
		try {
			httpConn = (HttpURLConnection) ((new URL(urlStr).openConnection()));
			httpConn.setDoOutput(true);
			httpConn.setRequestProperty("Content-Type", "application/json");
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestMethod("POST");
			httpConn.connect();
			byte[] outputBytes = params.getBytes(charSet);
			OutputStream out = httpConn.getOutputStream();
			out.write(outputBytes);
			out.close();
			return getResponseResult(httpConn, urlStr, charSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("请求失败：" + urlStr);
		} finally {
			if (null != httpConn) {
				httpConn.disconnect();
			}
		}
	}

	public static String httpsGet(String urlStr, String params, String charSet) throws Exception {
		HttpsURLConnection httpsConn = null;
		try {
			if (null != params && params.length() > 0) {
				if (urlStr.indexOf("?") == -1) {
					urlStr += "?" + params;
				} else {
					urlStr += "&" + params;
				}
			}
			byte[] data = null;
			if (params != null) {
				data = params.getBytes(charSet);
			}
			URL url = new URL(urlStr);
			HttpsHandler.trustAllHttpsCertificates();
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			httpsConn = (HttpsURLConnection) url.openConnection();
			httpsConn.setRequestMethod("GET");
			httpsConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
			httpsConn.setRequestProperty("Content-Length", String.valueOf(data == null ? 0 : data.length));
			httpsConn.setDoInput(true);
			httpsConn.setDoOutput(true);
			httpsConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
			httpsConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
			httpsConn.connect();
			/*
			 * OutputStream os1 = httpsConn.getOutputStream(); os1.write(data);
			 * os1.flush(); os1.close();
			 */
			return getResponseResult(httpsConn, urlStr, charSet);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(urlStr, ex);
			throw new Exception("请求失败：" + urlStr);
		} finally {
			if (null != httpsConn) {
				httpsConn.disconnect();
			}
		}
	}

	public static String getResponseResult(HttpURLConnection httpConn, String urlStr, String charSet)
			throws IOException {
		String res = null;
		// 获得响应状态
		int responseCode = httpConn.getResponseCode();
		if (HttpURLConnection.HTTP_OK == responseCode) {
			byte[] buffer = new byte[1024];
			int len = -1;
			InputStream is = httpConn.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			res = bos.toString(charSet);
			is.close();
			bos.close();
			logger.debug(urlStr + " Response Code:" + responseCode + " content:" + res);
		} else {
			logger.error(urlStr + " Response Code:" + responseCode);
		}
		return res;
	}

	/****************************** 新加方法 ******************************/
	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:30:34
	 * @param urlStr
	 *            请求地址
	 * @param queryString
	 *            参数列表
	 * @param headers
	 *            请求头信息列表
	 * @param method
	 *            请求方式：POST|GET
	 * @param connectTimeout
	 *            连接超时
	 * @param readTimeout
	 *            读写超时
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpRequest(String urlStr, String queryString, Map<String, String> headers, String method,
			int connectTimeout, int readTimeout, String charSet) throws Exception {
		HttpURLConnection httpConn = null;
		try {
			logger.debug("Request:" + urlStr + " headers:" + headers + " queryString:" + queryString);

			httpConn = (HttpURLConnection) ((new URL(urlStr).openConnection()));
			httpConn.setRequestMethod(method); // 设置请求方法：POST或者GET
			httpConn.setConnectTimeout(connectTimeout);// 连接超时 单位毫秒
			httpConn.setReadTimeout(readTimeout);// 读取超时 单位毫秒
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setChunkedStreamingMode(5);

			// 设置头信息
			String key = null;
			if (headers != null) {
				Iterator<String> iterator = headers.keySet().iterator();
				while (iterator.hasNext()) {
					key = iterator.next();
					httpConn.setRequestProperty(key, headers.get(key));
				}
			}
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("Charset", StringUtils.defaultString(charSet, DEFAULT_CHARSET));
			httpConn.setRequestProperty("Accept-Charset", StringUtils.defaultString(charSet, DEFAULT_CHARSET));
			byte[] outputBytes = StringUtils.defaultString(queryString, "").getBytes();
			httpConn.setRequestProperty("Content-Length", String.valueOf(outputBytes.length));
			httpConn.connect();

			OutputStreamWriter out = new OutputStreamWriter(httpConn.getOutputStream(), charSet);
			out.write(queryString);
			out.flush();
			out.close();

			return getResponseResult(httpConn, urlStr, charSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("请求失败：" + urlStr);
		} finally {
			if (null != httpConn) {
				httpConn.disconnect();
			}
		}
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:30:34
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息列表
	 * @param method
	 *            请求方式：POST|GET
	 * @param connectTimeout
	 *            连接超时
	 * @param readTimeout
	 *            读写超时
	 * @param charSet
	 *            编码
	 * @param isJson
	 *            是否发送json请求
	 * @return
	 * @throws Exception
	 */
	public static String httpRequest(String urlStr, Map<String, Object> params, Map<String, String> headers,
			String method, int connectTimeout, int readTimeout, String charSet, boolean isJson) throws Exception {
		// 组合参数
		String queryString = "";
		String key = "";
		if (params != null) {
			Iterator<String> ite = params.keySet().iterator();
			if (isJson) {
				JSONObject jsonObject = new JSONObject();
				while (ite.hasNext()) {
					key = ite.next();
					jsonObject.put(key, params.get(key));
				}
				queryString = jsonObject.toJSONString();
			} else {
				while (ite.hasNext()) {
					key = ite.next();
					queryString += key + "=" + URLEncoder.encode(params.get(key).toString(), DEFAULT_CHARSET) + "&";
				}
				if (queryString.endsWith("&")) {
					queryString = queryString.substring(0, queryString.length() - 1);
				}
			}
		}
		return httpRequest(urlStr, queryString, headers, method, connectTimeout, readTimeout, charSet);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息列表
	 * @param connectTimeout
	 *            链接超时
	 * @param readTimeout
	 *            读写超时
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String urlStr, Map<String, Object> params, Map<String, String> headers,
			int connectTimeout, int readTimeout, String charSet) throws Exception {
		if (headers == null)
			headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded;charset=" + charSet);
		return httpRequest(urlStr, params, headers, "POST", connectTimeout, readTimeout, charSet, false);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String urlStr, Map<String, Object> params, Map<String, String> headers,
			String charSet) throws Exception {
		return httpPost(urlStr, params, headers, DEFAULT_CONNECTTIMEOUT, DEFAULT_READTIMEOUT, charSet);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String urlStr, Map<String, Object> params, Map<String, String> headers)
			throws Exception {
		return httpPost(urlStr, params, headers, DEFAULT_CHARSET);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param jsonStr
	 *            参数json串
	 * @param headers
	 *            请求头信息
	 * @param connectTimeout
	 *            链接超时时间
	 * @param readTimeout
	 *            读写超时时间
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpPostJson(String urlStr, String jsonStr, Map<String, String> headers, int connectTimeout,
			int readTimeout, String charSet) throws Exception {
		if (headers == null)
			headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=" + charSet);
		headers.put("Accept", "application/json");
		return httpRequest(urlStr, jsonStr, headers, "POST", connectTimeout, readTimeout, charSet);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param jsonStr
	 *            参数json串
	 * @param headers
	 *            请求头信息
	 * @return
	 * @throws Exception
	 */
	public static String httpPostJson(String urlStr, String jsonStr, Map<String, String> headers) throws Exception {
		return httpPostJson(urlStr, jsonStr, headers, DEFAULT_CONNECTTIMEOUT, DEFAULT_READTIMEOUT, DEFAULT_CHARSET);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param connectTimeout
	 *            链接超时时间
	 * @param readTimeout
	 *            读写超时时间
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpPostJson(String urlStr, Map<String, Object> params, Map<String, String> headers,
			int connectTimeout, int readTimeout, String charSet) throws Exception {
		JSONObject json = new JSONObject();
		if (params != null) {
			Iterator<String> ite = params.keySet().iterator();
			String key = "";
			while (ite.hasNext()) {
				key = ite.next();
				json.put(key, params.get(key));
			}
		}
		return httpPostJson(urlStr, json.toJSONString(), headers, connectTimeout, readTimeout, charSet);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpPostJson(String urlStr, Map<String, Object> params, Map<String, String> headers,
			String charSet) throws Exception {
		return httpPostJson(urlStr, params, headers, DEFAULT_CONNECTTIMEOUT, DEFAULT_READTIMEOUT, charSet);
	}

	/**
	 * 说明：发送post请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpPostJson(String urlStr, Map<String, Object> params, Map<String, String> headers)
			throws Exception {
		return httpPostJson(urlStr, params, headers, DEFAULT_CHARSET);
	}

	/**
	 * 说明：发送get请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param connectTimeout
	 *            链接超时时间
	 * @param readTimeout
	 *            读写超时时间
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String urlStr, Map<String, Object> params, Map<String, String> headers,
			int connectTimeout, int readTimeout, String charSet) throws Exception {
		return httpRequest(urlStr, params, headers, "GET", connectTimeout, readTimeout, charSet, false);
	}

	/**
	 * 说明：发送get请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String urlStr, Map<String, Object> params, Map<String, String> headers, String charSet)
			throws Exception {
		return httpGet(urlStr, params, headers, DEFAULT_CONNECTTIMEOUT, DEFAULT_READTIMEOUT, charSet);
	}

	/**
	 * 说明：发送get请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @param charSet
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String urlStr, Map<String, Object> params, Map<String, String> headers)
			throws Exception {
		return httpGet(urlStr, params, headers, DEFAULT_CHARSET);
	}

	/**
	 * 说明：发送get请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @param params
	 *            参数列表
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String urlStr, Map<String, Object> params) throws Exception {
		return httpGet(urlStr, params, new HashMap<String, String>(), DEFAULT_CHARSET);
	}

	/**
	 * 说明：发送get请求. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017-5-12 上午11:54:01
	 * @param urlStr
	 *            请求地址
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String urlStr) throws Exception {
		return httpGet(urlStr, null, new HashMap<String, String>(), DEFAULT_CHARSET);
	}

	// public static void main(String[] args) throws Exception {
	/*
	 * String url =
	 * "https://ydbs.nb-n-tax.gov.cn:7001/GSAPPMobile/fpxx?taxpayer_code=330382198409277114";
	 * try { String result = httpGet(url, "", DEFAULT_CHARSET);
	 * System.out.println(result); } catch (Exception e) { e.printStackTrace();
	 * }
	 */

	// /v1/token
	/*
	 * Map<String, Object> params = new HashMap<String, Object>();
	 * params.put("grant_type", "client_credentials"); params.put("client_id",
	 * "1000"); params.put("client_secret", "557a7bdaacd1e48aa7f6d444671b9ed4");
	 *
	 * String urlStr = "https://dev.rrtimes.com/v1/token";
	 * HttpUtils.httpPostJson(urlStr, params, null);
	 */

	// HttpUtils.httpPost("http://127.0.0.1:8881/bus/refresh", new
	// HashMap<String, Object>(),
	// new HashMap<String, String>());
	// }

}
