package com.jusfoun.common.base;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.mybatis.page.IPage;
import com.jusfoun.common.mybatis.page.IPageable;
import com.jusfoun.common.result.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 描述: Controller基类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
@Api
public abstract class BaseController<T extends IPageable, PK extends Serializable> {

	@Autowired
	private BaseService<T> baseService;

	@ApiOperation(value = "保存数据", notes = "保存数据", hidden = false)
	@RequestMapping(value = "baseSave", method = {RequestMethod.POST})
	public BaseResponse<T> baseSave(@RequestBody T t) {
		baseService.insert(t);
		return BaseResponse.success(t);
	}

	@ApiOperation(value = "修改数据", notes = "修改数据", hidden = false)
	@RequestMapping(value = "baseUpdate", method = {RequestMethod.POST})
	public BaseResponse<T> baseUpdate(@RequestBody T t) {
		baseService.updateByPrimaryKeySelective(t);
		return BaseResponse.success(t);
	}

	@ApiOperation(value = "删除数据", notes = "删除数据", hidden = false)
	@RequestMapping(value = "baseDelete", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<?> baseDelete(PK id) {
		baseService.deleteByPrimaryKey(id);
		return BaseResponse.success();
	}

	@ApiOperation(value = "查询数据列表", notes = "查询数据列表", hidden = false)
	@RequestMapping(value = "baseList", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<IPage<T>> baseList(@RequestBody T t) {
		IPage<T> page = baseService.selectPage(t, new IPage<T>(t.isPageable(), t.getPageNum(), t.getPageSize()));
		return BaseResponse.success(page);
	}

	@ApiOperation(value = "查询数据详情", notes = "查询数据详情", hidden = false)
	@RequestMapping(value = "baseInfo", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<T> baseInfo(PK id) {
		T t = baseService.selectByPrimaryKey(id);
		return BaseResponse.success(t);
	}
}
