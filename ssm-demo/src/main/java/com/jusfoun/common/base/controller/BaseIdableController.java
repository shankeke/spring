package com.jusfoun.common.base.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jusfoun.common.base.Idable;
import com.jusfoun.common.base.service.BaseIdableService;
import com.jusfoun.common.result.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述: 根据ID操作的接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
@Api
public interface BaseIdableController<T extends Idable<PK>, PK extends Serializable> {

	BaseIdableService<T> getBaseIdableService();

	@ApiOperation(value = "根据ID修改数据", notes = "根据ID修改数据", hidden = false)
	@RequestMapping(value = "updateById", method = { RequestMethod.POST })
	default BaseResponse<T> updateById(@ApiParam(value = "数据对象", required = true) @RequestBody T t) {
		getBaseIdableService().updateByPrimaryKeySelective(t);
		return BaseResponse.success(t);
	}

	@ApiOperation(value = "根据ID批量修改数据", notes = "根据ID批量修改数据", hidden = false)
	@RequestMapping(value = "updateListById", method = { RequestMethod.POST })
	default BaseResponse<?> updateListById(@ApiParam(value = "数据对象集合", required = true) @RequestBody List<T> list) {
		getBaseIdableService().updateListByPrimaryKeySelective(list);
		return BaseResponse.success();
	}

	@ApiOperation(value = "根据ID删除数据", notes = "根据ID删除数据", hidden = false)
	@RequestMapping(value = "deleteById", method = { RequestMethod.POST, RequestMethod.GET })
	default BaseResponse<?> deleteById(@ApiParam(value = "主键值", required = true) @RequestParam PK id) {
		getBaseIdableService().deleteByPrimaryKey(id);
		return BaseResponse.success();
	}

	@ApiOperation(value = "根据ID批量删除数据", notes = "根据ID批量删除数据", hidden = false)
	@RequestMapping(value = "deleteByIds", method = { RequestMethod.POST, RequestMethod.GET })
	default BaseResponse<?> deleteByIds(@ApiParam(value = "ID集合", required = true) @RequestParam List<PK> ids) {
		getBaseIdableService().deleteByPrimaryKeys(ids);
		return BaseResponse.success();
	}

	@ApiOperation(value = "查询数据详情", notes = "查询数据详情", hidden = false)
	@RequestMapping(value = "infoById", method = { RequestMethod.POST, RequestMethod.GET })
	default BaseResponse<T> infoById(@ApiParam(value = "主键值", required = true) @RequestParam PK id) {
		T t = getBaseIdableService().selectByPrimaryKey(id);
		return BaseResponse.success(t);
	}
}
