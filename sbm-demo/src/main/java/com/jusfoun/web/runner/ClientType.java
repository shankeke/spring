package com.jusfoun.web.runner;

import com.jusfoun.common.mybatis.typehandler.enumtype.StringValuable;

/**
 * 描述:客户端类型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月7日 下午2:27:27
 */
public enum ClientType implements StringValuable {

	APP_CLIENT("app_client"), //
	WEB_CLIENT("web_client"), //
	BIGDATA_CLIENT("bigdata_client");

	private final String clientId;

	private ClientType(String clientId) {
		this.clientId = clientId;
	}

	public String getClientId() {
		return clientId;
	}

	@Override
	public String getValue() {
		return clientId;
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(ClientType.APP_CLIENT.valIn("app_client", "2323"));
	 * System.out.println(ClientType.APP_CLIENT.valIn(new String[]{"app_client",
	 * "2323"}));
	 * System.out.println(ClientType.APP_CLIENT.valNotIn("app_client", "2323"));
	 * System.out.println(ClientType.APP_CLIENT.valNotIn(new
	 * String[]{"app_client", "2323"})); }
	 */
}
