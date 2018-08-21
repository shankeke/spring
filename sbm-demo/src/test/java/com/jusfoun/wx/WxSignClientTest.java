package com.jusfoun.wx;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.jusfoun.client.BaseClient;

/**
 * 描述 :微信签名获取接口测试. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月21日 下午1:57:54
 */
public class WxSignClientTest extends BaseClient<JSONObject> {

	@Test
	public void sign() {
		t.put("url", "http://tjhx.9cfcf.com/aobo/app/index.html");
		json("/wx/sign", t);
	}
}
