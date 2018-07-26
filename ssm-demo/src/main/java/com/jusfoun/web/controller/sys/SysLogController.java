package com.jusfoun.web.controller.sys;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.entity.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 描述 : 系统日志管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:07:10
 */
@Api(description = "操作日志管理", value = "操作日志管理接口类")
@RestController
@RequestMapping("/syslog")
public class SysLogController extends BaseController<SysLog, Long> {

	@ApiOperation(value = "saveBase", hidden = true)
	@Override
	public BaseResponse<SysLog> saveBase(SysLog t) {
		return super.saveBase(t);
	}

	@ApiOperation(value = "saveList", hidden = true)
	@Override
	public BaseResponse<?> saveList(List<SysLog> list) {
		return super.saveList(list);
	}

	@ApiOperation(value = "updateBase", hidden = true)
	@Override
	public BaseResponse<SysLog> updateBase(SysLog t) {
		return super.updateBase(t);
	}

	@ApiOperation(value = "updateList", hidden = true)
	@Override
	public BaseResponse<?> updateList(List<SysLog> list) {
		return super.updateList(list);
	}
}
