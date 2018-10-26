package com.jusfoun.client;

import java.util.Map;

import org.junit.Test;

import com.beust.jcommander.internal.Maps;
import com.jusfoun.entity.SysModule;

/**
 * 说明：权限管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午5:57:26
 */
public class SysModuleClientTest extends BaseClient<SysModule> {

	@Override
	public void list() {
		// for (int i = 0; i < 10000; i++) {
		t.setParentId(1L);
		json("/sysmodule/list", t);
		// }
	}

	@Test
	public void getTree() {
		// for (int i = 0; i < 10000; i++) {
		// t.setParentId(1L);
		Map<String, Object> params = Maps.newHashMap();
		params.put("rootId", "1");
		params.put("keyword", "生产计划");
		form("/sysmodule/getTree", params);
		// }
	}
}
