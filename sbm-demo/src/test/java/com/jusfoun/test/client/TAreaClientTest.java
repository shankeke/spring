package com.jusfoun.test.client;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.jusfoun.entity.TArea;

/**
 * 说明：地区信息管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午11:00:47
 */
public class TAreaClientTest extends BaseClient<TArea> {

	@Test
	public void list() {
		t.setLevel((byte) 4);
		json("/area/list", t);
	}

	@Test
	public void getTree() {
		Map<String, Object> params = Maps.newHashMap();
		params.put("keyword", "北京");
		form("/area/getTree", params);
	}

}
