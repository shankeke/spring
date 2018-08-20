package com.jusfoun.client;

import com.jusfoun.entity.SysLog;

/**
 * 描述 : 系统日志查询. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月11日 下午6:04:01
 */
public class SysLogClientTest extends BaseClient<SysLog> {

	@Override
	public void list() {
		t.setPageNum(3);
		rest("/syslog/list", t);
	}

}
