package com.jusfoun.client;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.jusfoun.entity.TArea;

/**
 * 描述 :地区信息管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午11:00:47
 */
public class TAreaClientTest extends BaseClient<TArea> {

	@Test
	public void baseList() {
		t.setLevel((byte) 4);
		rest("/area/baseList", t);
	}
	
	@Test
	public void getAreaTree() {
		Map<String, Object> params = Maps.newHashMap();
		params.put("areaName", "北京");
		post("/area/getAreaTree", params);
	}

}
