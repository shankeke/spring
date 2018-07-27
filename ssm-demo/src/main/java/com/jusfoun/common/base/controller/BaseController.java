package com.jusfoun.common.base.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.message.result.BaseResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述:基本操作接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
public interface BaseController<T> {

	BaseService<T> getBaseService();

	@ApiOperation(value = "保存数据", notes = "保存数据", hidden = false)
	@RequestMapping(value = "save", method = { RequestMethod.POST })
	default BaseResponse<T> save(@ApiParam(value = "数据对象", required = true) @RequestBody T t) {
		getBaseService().insert(t);
		return BaseResponse.success(t);
	}

	@ApiOperation(value = "批量保存数据", notes = "批量保存数据", hidden = false)
	@RequestMapping(value = "saveList", method = { RequestMethod.POST })
	default BaseResponse<?> saveList(@ApiParam(value = "数据对象集合", required = true) @RequestBody List<T> list) {
		getBaseService().insertListSelective(list);
		return BaseResponse.success();
	}

	@ApiOperation(value = "查询数据详情", notes = "查询数据详情", hidden = false)
	@RequestMapping(value = "info", method = { RequestMethod.POST, RequestMethod.GET })
	default BaseResponse<T> info(@ApiParam(value = "查询参数", required = true) @RequestBody T t) {
		t = getBaseService().selectOne(t);
		return BaseResponse.success(t);
	}

}
