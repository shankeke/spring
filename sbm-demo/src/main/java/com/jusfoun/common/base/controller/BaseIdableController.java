package com.jusfoun.common.base.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.id.Idable;
import com.jusfoun.common.base.service.BaseIdableService;
import com.jusfoun.common.message.exception.ControllerException;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.common.message.result.ErrType;
import com.jusfoun.common.utils.ICollections;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 说明： 根据ID操作的接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
@RestController
public interface BaseIdableController<T extends Idable<PK>, PK extends Serializable> extends BaseController<T> {

	BaseIdableService<T> getBaseIdableService();

	@ApiOperation(value = "根据ID修改数据", notes = "根据ID修改数据", hidden = false)
	@PostMapping("updateById")
	default BaseResponse<T> updateById(//
			@ApiParam(value = "数据对象", required = true) @RequestBody T t//
	) {
		if (t == null) {
			throw new ControllerException(ErrType.BAD_REQUEST);
		}
		try {
			getBaseIdableService().updateByPrimaryKeySelective(t);
		} catch (Exception e) {
			log.error(ErrType.ENTITY_UPDATE_ERROR.getMessage(), e);
			throw new ControllerException(ErrType.ENTITY_UPDATE_ERROR);
		}
		return BaseResponse.success(t);
	}

	@ApiOperation(value = "根据ID批量修改数据", notes = "根据ID批量修改数据", hidden = false)
	@PostMapping("updateListById")
	default BaseResponse<?> updateListById(//
			@ApiParam(value = "数据对象集合", required = true) @RequestBody List<T> list//
	) {
		if (list == null || ICollections.hasNoElements(list)) {
			throw new ControllerException(ErrType.BAD_REQUEST);
		}
		try {
			getBaseIdableService().updateListByPrimaryKeySelective(list);
		} catch (Exception e) {
			log.error(ErrType.ENTITY_UPDATE_ERROR.getMessage(), e);
			throw new ControllerException(ErrType.ENTITY_UPDATE_ERROR);
		}
		return BaseResponse.success();
	}

	@ApiOperation(value = "根据ID删除数据", notes = "根据ID删除数据", hidden = false)
	@PostMapping("deleteById")
	default BaseResponse<?> deleteById(//
			@ApiParam(value = "主键值", required = true) @RequestParam PK id//
	) {
		if (id == null) {
			throw new ControllerException(ErrType.BAD_REQUEST);
		}
		try {
			getBaseIdableService().deleteByPrimaryKey(id);
		} catch (Exception e) {
			log.error(ErrType.ENTITY_DELETE_ERROR.getMessage(), e);
			throw new ControllerException(ErrType.ENTITY_DELETE_ERROR);
		}
		return BaseResponse.success();
	}

	@ApiOperation(value = "根据ID批量删除数据", notes = "根据ID批量删除数据", hidden = false)
	@PostMapping("deleteByIds")
	default BaseResponse<?> deleteByIds(//
			@ApiParam(value = "ID集合", required = true) @RequestParam List<PK> ids//
	) {
		if (ids == null || ICollections.hasNoElements(ids)) {
			throw new ControllerException(ErrType.BAD_REQUEST);
		}
		try {
			getBaseIdableService().deleteByPrimaryKeys(ids);
		} catch (Exception e) {
			log.error(ErrType.ENTITY_DELETE_ERROR.getMessage(), e);
			throw new ControllerException(ErrType.ENTITY_DELETE_ERROR);
		}
		return BaseResponse.success();
	}

	@ApiOperation(value = "查询数据详情", notes = "查询数据详情", hidden = false)
	@PostMapping("infoById")
	default BaseResponse<T> infoById(//
			@ApiParam(value = "主键值", required = true) @RequestParam PK id//
	) {
		if (id == null) {
			throw new ControllerException(ErrType.BAD_REQUEST);
		}
		T t = null;
		try {
			t = getBaseIdableService().selectByPrimaryKey(id);
		} catch (Exception e) {
			log.error(ErrType.ENTITY_QUERY_INFO_ERROR.getMessage(), e);
			throw new ControllerException(ErrType.ENTITY_QUERY_INFO_ERROR);
		}
		return BaseResponse.success(t);
	}
}
