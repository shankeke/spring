package com.jusfoun.client;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.jusfoun.entity.TCountry;

/**
 * 描述: 国家信息接口测试. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午4:54:13
 */
public class TCountryClientTest extends BaseClient<TCountry> {

	@Override
	public void list() {
		t.setShName("朝鲜");
		rest("/country/list", t);
	}

	@Test
	public void infoById() {
		Map<String, Object> params = Maps.newHashMap();
		params.put("id", "1");
		post("/country/infoById", params);
	}

}
