package com.jusfoun.common.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.base.page.IPageable;
import com.jusfoun.common.base.service.BaseIdableService;
import com.jusfoun.common.result.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述: Controller基类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
@Api
public abstract class BaseController<T extends IPageable & Idable<PK>, PK extends Serializable> {

	@Autowired
	private BaseIdableService<T> baseIdableService;

	@ApiOperation(value = "保存数据对象", notes = "保存数据对象", hidden = false)
	@RequestMapping(value = "saveBase", method = {RequestMethod.POST})
	public BaseResponse<T> saveBase(@ApiParam(value = "数据对象", required = true) @RequestBody T t) {
		baseIdableService.insert(t);
		return BaseResponse.success(t);
	}

	@ApiOperation(value = "保存数据对象集合", notes = "保存数据对象集合", hidden = false)
	@RequestMapping(value = "saveList", method = {RequestMethod.POST})
	public BaseResponse<?> saveList(@ApiParam(value = "数据对象集合", required = true) @RequestBody List<T> list) {
		baseIdableService.insertListSelective(list);
		return BaseResponse.success();
	}

	@ApiOperation(value = "修改数据对象", notes = "修改数据对象", hidden = false)
	@RequestMapping(value = "updateBase", method = {RequestMethod.POST})
	public BaseResponse<T> updateBase(@ApiParam(value = "数据对象", required = true) @RequestBody T t) {
		baseIdableService.updateByPrimaryKeySelective(t);
		return BaseResponse.success(t);
	}

	@ApiOperation(value = "修改数据对象集合", notes = "修改数据对象集合", hidden = false)
	@RequestMapping(value = "updateList", method = {RequestMethod.POST})
	public BaseResponse<?> updateList(@ApiParam(value = "数据对象集合", required = true) @RequestBody List<T> list) {
		baseIdableService.updateListByPrimaryKeySelective(list);
		return BaseResponse.success();
	}

	@ApiOperation(value = "删除数据对象", notes = "删除数据对象", hidden = false)
	@RequestMapping(value = "deleteBase", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<?> deleteBase(@ApiParam(value = "主键值", required = true) @RequestParam PK id) {
		baseIdableService.deleteByPrimaryKey(id);
		return BaseResponse.success();
	}

	@ApiOperation(value = "删除数据对象集合", notes = "删除数据对象集合", hidden = false)
	@RequestMapping(value = "deleteList", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<?> deleteBase(@ApiParam(value = "主键值集合", required = true) @RequestParam List<PK> ids) {
		baseIdableService.deleteByPrimaryKeys(ids);
		return BaseResponse.success();
	}

	@ApiOperation(value = "查询数据对象列表", notes = "查询数据对象列表", hidden = false)
	@RequestMapping(value = "listBase", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<IPage<T>> listBase(@ApiParam(value = "查询参数", required = true) @RequestBody T t) {
		IPage<T> page = baseIdableService.selectPage(t, new IPage<T>(t));
		return BaseResponse.success(page);
	}

	@ApiOperation(value = "查询数据对象详情", notes = "查询数据对象详情", hidden = false)
	@RequestMapping(value = "infoBase", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<T> infoBase(@ApiParam(value = "主键值", required = true) @RequestParam PK id) {
		T t = baseIdableService.selectByPrimaryKey(id);
		return BaseResponse.success(t);
	}
}
