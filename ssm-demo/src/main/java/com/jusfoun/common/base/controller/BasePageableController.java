package com.jusfoun.common.base.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.base.page.IPageable;
import com.jusfoun.common.message.result.BaseResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述:分页查询的接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
public interface BasePageableController<T extends IPageable> extends BaseController<T> {

	@ApiOperation(value = "查询数据对象列表", notes = "查询数据对象列表，返回对象集合", hidden = false)
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	default BaseResponse<List<T>> list(@ApiParam(value = "查询参数", required = true) @RequestBody T t) {
		List<T> list = getBaseService().selectByPage(t, new IPage<T>(t));
		return BaseResponse.success(list);
	}

	@ApiOperation(value = "查询数据对象列表", notes = "查询数据对象列表，返回分页对象", hidden = false)
	@RequestMapping(value = "listPage", method = { RequestMethod.POST, RequestMethod.GET })
	default BaseResponse<IPage<T>> listPage(@ApiParam(value = "查询参数", required = true) @RequestBody T t) {
		IPage<T> page = getBaseService().selectPage(t, new IPage<T>(t));
		return BaseResponse.success(page);
	}
}
