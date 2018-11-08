package com.jusfoun.test.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class TestApiClient extends BaseClient<JSONObject> {

	@Test
	public void getProducts() {
		t.put("n", 5);
		get("/test/getProducts", t);
	}

	@Test
	public void getProductList() {
		t.put("n", 5);
		get("/test/getProductList", t);
	}

}
