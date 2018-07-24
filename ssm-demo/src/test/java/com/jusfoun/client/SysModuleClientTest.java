package com.jusfoun.client;

import com.jusfoun.entity.SysModule;

/**
 * 描述 :权限管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午5:57:26
 */
public class SysModuleClientTest extends BaseClient<SysModule> {

	@Override
	public void list() {
		// for (int i = 0; i < 10000; i++) {
		t.setParentId(1L);
		rest("/sysmodule/list", t);
		// }
	}
}
