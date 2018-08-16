package com.jusfoun.wx;

import org.junit.Test;

import com.jusfoun.common.utils.net.HttpUtils;

public class WxSignTest {

	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
//	private static final String TOKEN_APPID = "wx2b9dd3d245d3080e";
//	private static final String TOKEN_APPSECRET = "a05a0da1762607da64ab61d74a07f022";
	private static final String TOKEN_APPID = "wxf008a5b469afee03";
	private static final String TOKEN_APPSECRET = "27fe78ccc24d16291ad38a17a9da344d";

	// appID wx2b9dd3d245d3080e
	// appsecret a05a0da1762607da64ab61d74a07f022
	@Test
	public void token() throws Exception {
		String urlStr = String.format(TOKEN_URL, TOKEN_APPID, TOKEN_APPSECRET);
		String httpGet = HttpUtils.httpsGet(urlStr, null, "utf-8");
		System.out.println(httpGet);
	}

	// {"access_token":"v_C5zfR-b7eOxjlsyvBO3UbXS4dlbfVIps_TGcsh8uOcgckaBodt23V_IrvTMS12PS9urZTiaaF2J8_TMUFEp35I0UOyfh3q_6YDGd6WQtYNYSbAHAWHB","expires_in":7200}
	// {"errcode":0,"errmsg":"ok","ticket":"HoagFKDcsGMVCIY2vOjf9sO7T4V_NywFGX4e63CSu9JmsjhD1KGQrNSNGDL7i6aZNCnUmoQTqUcZd0tJnlqcHg","expires_in":7200}

	@Test
	public void getticket() throws Exception {
		String access_token = "v_C5zfR-b7eOxjlsyvBO3UbXS4dlbfVIps_TGcsh8uOcgckaBodt23V_IrvTMS12PS9urZTiaaF2J8_TMUFEp35I0UOyfh3q_6YDGd6WQtYNYSbAHAWHB";
		String urlStr = String.format(JSAPI_TICKET_URL, access_token);
		String httpGet = HttpUtils.httpsGet(urlStr, null, "utf-8");
		System.out.println(httpGet);
	}
}
