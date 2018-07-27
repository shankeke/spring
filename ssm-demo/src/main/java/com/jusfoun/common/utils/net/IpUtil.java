package com.jusfoun.common.utils.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述 : IP相关方法. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月10日 下午7:52:46
 */
public class IpUtil {

	private static final String URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

	/**
	 * 描述 : 根据IP地址查询IP地址所属地区. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月10日 下午7:39:22
	 * @param ip
	 *            IP地址
	 * @return IP所属的地区
	 * @throws Exception
	 */
	public static String getAreaByIp(String ip) throws Exception {
		String httpGet = HttpUtils.httpGet(URL + ip);
		if (StringUtils.isEmpty(httpGet)) {
			return "";
		}
		JSONObject json = null;
		try {
			json = JSON.parseObject(httpGet);
		} catch (Exception e) {
			return "";
		}
		if (json == null) {
			return "";
		}
		String address = StringUtils.defaultString(json.getString("country"), "") + " "
				+ StringUtils.defaultString(json.getString("province"), "") + " "
				+ StringUtils.defaultString(json.getString("city"), "") + " "
				+ StringUtils.defaultString(json.getString("district"), "");
		return address.trim();
	}

	/**
	 * 获取服务器IP地址
	 *
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String getServerIp() {
		String SERVER_IP = null;
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				ip = (InetAddress) ni.getInetAddresses().nextElement();
				SERVER_IP = ip.getHostAddress();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
					SERVER_IP = ip.getHostAddress();
					break;
				} else {
					ip = null;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return SERVER_IP;
	}

	/**
	 * 描述 : 获取IP地址. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月14日 上午11:30:12
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
