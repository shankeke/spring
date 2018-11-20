package com.jusfoun.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.controller.BasePageableAndIdableController;
import com.jusfoun.common.base.service.BaseIdableService;
import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.entity.SysLog;
import com.jusfoun.service.SysLogService;

import io.swagger.annotations.Api;

/**
 * 说明： 系统日志管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:07:10
 */
@Api(tags = "SYS-SysLogController", description = "操作日志管理", value = "操作日志管理接口类")
@RestController
@RequestMapping(value = {"/v1/syslog", "/app/syslog"})
public class SysLogController implements BasePageableAndIdableController<SysLog, Long> {
	@Autowired
	private SysLogService sysLogService;

	@Override
	public BaseIdableService<SysLog> getBaseIdableService() {
		return sysLogService;
	}

	@Override
	public BaseService<SysLog> getBaseService() {
		return sysLogService;
	}

}
