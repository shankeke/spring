package com.jusfoun.common.base.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.base.page.IPageable;
import com.jusfoun.common.message.exception.ControllerException;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.common.message.result.ErrType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 说明：分页查询的接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
@RestController
public interface BasePageableController<T extends IPageable> extends BaseController<T> {

	@ApiOperation(value = "查询数据对象列表", notes = "查询数据对象列表，返回对象集合", hidden = false)
	@PostMapping("list")
	default BaseResponse<List<T>> list(//
			@ApiParam(value = "查询参数", required = true) @RequestBody T t//
	) {
		if (t == null) {
			throw new ControllerException(ErrType.BAD_REQUEST);
		}
		List<T> list = null;
		try {
			list = getBaseService().selectByPage(t, new IPage<T>(t));
		} catch (Exception e) {
			log.error(ErrType.ENTITY_QUERY_LIST_ERROR.getMessage(), e);
			throw new ControllerException(ErrType.ENTITY_QUERY_LIST_ERROR);
		}
		return BaseResponse.success(list);
	}

	@ApiOperation(value = "查询数据对象列表", notes = "查询数据对象列表，返回分页对象", hidden = false)
	@PostMapping("listPage")
	default BaseResponse<IPage<T>> listPage(//
			@ApiParam(value = "查询参数", required = true) @RequestBody T t//
	) {
		if (t == null) {
			throw new ControllerException(ErrType.BAD_REQUEST);
		}
		IPage<T> page = null;
		try {
			page = getBaseService().selectPage(t, new IPage<T>(t));
		} catch (Exception e) {
			log.error(ErrType.ENTITY_QUERY_LIST_ERROR.getMessage(), e);
			throw new ControllerException(ErrType.ENTITY_QUERY_LIST_ERROR);
		}
		return BaseResponse.success(page);
	}
}
