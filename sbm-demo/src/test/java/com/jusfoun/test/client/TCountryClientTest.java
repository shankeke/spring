package com.jusfoun.test.client;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.jusfoun.entity.TCountry;

/**
 * 说明： 国家信息接口测试. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午4:54:13
 */
public class TCountryClientTest extends BaseClient<TCountry> {

	@Override
	public void list() {
		t.setShName("朝鲜");
		json("/country/list", t);
	}

	@Test
	public void infoById() {
		Map<String, Object> params = Maps.newHashMap();
		params.put("id", "1");
		form("/country/infoById", params);
	}

}