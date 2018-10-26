package com.jusfoun.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 说明： 缓存管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月22日 上午10:07:54
 */
public class CacheManageClientTest extends BaseClient<JSONObject> {

	@Test
	public void list() {
		json("/cache/list", t);
	}

	@Test
	public void clear() {
		// t.put("cacheName", "customCache");
		json("/cache/clear", t);
	}

	@Test
	public void put() {
		t.put("paramKey", "key");
		t.put("paramValue", "value");
		t.put("cacheName", "customCache");
		json("/cache/put", t);
	}

}
