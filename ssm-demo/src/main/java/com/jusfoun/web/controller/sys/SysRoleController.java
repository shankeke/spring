package com.jusfoun.web.controller.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beust.jcommander.internal.Maps;
import com.jusfoun.common.annotation.Logable;
import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.mybatis.page.IPage;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.entity.SysRole;
import com.jusfoun.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述 :系统角色管理接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午4:11:34
 */
@Api(description = "系统角色管理", value = "系统角色管理接口类")
@RestController
@RequestMapping("/sysrole")
public class SysRoleController extends BaseController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 描述 : 系统角色保存. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午4:13:03
	 * @param sysRole
	 *            角色
	 * @return 结果
	 */
	@ApiOperation(value = "保存角色信息", notes = "保存角色信息", hidden = false)
	@Logable(desc = "保存角色", fullPath = "系统管理/角色管理/保存角色")
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public BaseResponse<?> save(@ApiParam(value = "可只传非空的字段", required = true) @RequestBody SysRole sysRole) {
		if (sysRole == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}

		if (StringUtils.isEmpty(sysRole.getName())) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR, "角色名称不能为空");
		}

		// 判断角色是否已经存在
		SysRole record = new SysRole();
		record.setName(sysRole.getName());
		int count = sysRoleService.selectCount(record);
		if (count > 0) {
			return BaseResponse.fail(ErrType.SYSROLE_ENTITY_EXIST_ERROR);
		}

		try {
			sysRoleService.saveWithAssociate(sysRole);
			return BaseResponse.success();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_SAVE_ERROR);
		}
	}

	/**
	 * 描述 : 系统角色更新. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午4:13:03
	 * @param sysRole
	 *            角色
	 * @return 结果
	 */
	@ApiOperation(value = "修改角色信息", notes = "修改角色信息", hidden = false)
	@Logable(desc = "修改角色", fullPath = "系统管理/角色管理/修改角色")
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	public BaseResponse<?> update(@ApiParam(value = "可只传修改的字段，必传ID", required = true) @RequestBody SysRole sysRole) {
		if (sysRole == null || sysRole.getId() == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}
		try {
			sysRoleService.updateWithAssociate(sysRole);
			return BaseResponse.success();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_UPDATE_ERROR);
		}
	}

	/**
	 * 描述 : 系统角色删除. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午4:13:03
	 * @param sysRole
	 *            角色
	 * @return 结果
	 */
	@ApiOperation(value = "删除角色信息", notes = "删除角色信息", hidden = false)
	@Logable(desc = "删除角色", fullPath = "系统管理/角色管理/删除角色")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<?> delete(@ApiParam(value = "删除记录的ID", required = true) @RequestParam Long id) {
		try {
			sysRoleService.deleteRoleWithModules(id);
			return BaseResponse.success();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_DELETE_ERROR);
		}
	}

	/**
	 * 描述 : 系统角色列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午4:13:03
	 * @param sysRole
	 *            角色
	 * @return 结果
	 */
	@ApiOperation(value = "查询角色列表信息", notes = "查询角色列表信息", hidden = false)
	@Logable(desc = "查询角色列表", fullPath = "系统管理/角色管理/查询角色列表")
	// @PreAuthorize("hasPermission('user', '/sysrole/list')")
	// @Authentication(value = "/sysrole/save", enable = true, desc = "查询角色列表")
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public BaseResponse<IPage<SysRole>> list(//
			@ApiParam(value = "是否分页：分页-true，不分页-false，默认true", defaultValue = "true") @RequestParam(name = "pageable", defaultValue = "true", required = false) boolean pageable, //
			@ApiParam(value = "页码", defaultValue = "1") @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum, //
			@ApiParam(value = "页长", defaultValue = "10") @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize //
	) {
		try {
			IPage<SysRole> page = new IPage<>(pageable, pageNum, pageSize, "id ASC");
			page = sysRoleService.selectPageWithAssociate(Maps.newHashMap(), page);
			return BaseResponse.success(page);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_QUERY_LIST_ERROR);
		}
	}

	/**
	 * 描述 : 系统角色详情. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午4:13:03
	 * @param sysRole
	 *            角色
	 * @return 结果
	 */
	@ApiOperation(value = "查询角色详情", notes = "查询角色详情", hidden = false)
	@Logable(desc = "查询角色详情", fullPath = "系统管理/角色管理/查询角色详情")
	@RequestMapping(value = "/info", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<SysRole> info(@ApiParam(value = "删除记录的ID", required = true) @RequestParam Long id) {
		try {
			return BaseResponse.success(sysRoleService.selectPKWithAssociate(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_QUERY_INFO_ERROR);
		}
	}
}
