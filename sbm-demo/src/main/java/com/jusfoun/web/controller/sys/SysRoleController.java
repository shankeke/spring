package com.jusfoun.web.controller.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.controller.BasePageableAndIdableController;
import com.jusfoun.common.base.service.BaseIdableService;
import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.log.Logable;
import com.jusfoun.common.message.annotation.JsonBody;
import com.jusfoun.common.message.annotation.JsonBodys;
import com.jusfoun.common.message.exception.ControllerException;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.common.message.result.ErrType;
import com.jusfoun.entity.SysRole;
import com.jusfoun.service.SysRoleService;

import io.swagger.annotations.Api;

/**
 * 描述 :系统角色管理接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午4:11:34
 */
@Api(description = "系统角色管理", value = "系统角色管理接口类")
@RestController
@RequestMapping("/sysrole")
public class SysRoleController implements BasePageableAndIdableController<SysRole, Long> {

	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public BaseService<SysRole> getBaseService() {
		return sysRoleService;
	}

	@Override
	public BaseIdableService<SysRole> getBaseIdableService() {
		return sysRoleService;
	}

	@Logable(value = "保存角色", path = "系统管理/角色管理/保存角色")
	@Override
	public BaseResponse<SysRole> save(SysRole t) {
		if (t == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}
		if (StringUtils.isEmpty(t.getName())) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR, "角色名称不能为空");
		}

		// 判断角色是否已经存在
		SysRole record = new SysRole();
		record.setName(t.getName());
		int count = sysRoleService.selectCount(record);
		if (count > 0) {
			return BaseResponse.fail(ErrType.SYSROLE_ENTITY_EXIST_ERROR);
		}

		try {
			sysRoleService.saveWithAssociate(t);
			return BaseResponse.success();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_SAVE_ERROR);
		}
	}

	@Logable(value = "修改角色", path = "系统管理/角色管理/修改角色")
	@Override
	public BaseResponse<SysRole> updateById(SysRole t) {
		if (t == null || t.getId() == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}
		try {
			sysRoleService.updateWithAssociate(t);
			return BaseResponse.success();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_UPDATE_ERROR);
		}
	}

	@Logable(value = "删除角色", path = "系统管理/角色管理/删除角色")
	@Override
	public BaseResponse<?> deleteById(Long id) {
		try {
			sysRoleService.deleteRoleWithModules(id);
			return BaseResponse.success();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_DELETE_ERROR);
		}
	}

	@JsonBodys({@JsonBody(type = SysRole.class, excludes = {"createId", "creatorName", "createDate", "updateId", "updaterName", "updateDate"})})
	@Logable(value = "查询角色详情", path = "系统管理/角色管理/查询角色详情", message = "'根据角色ID（' + #id + '）查询角色详情信息'")
	@Override
	public BaseResponse<SysRole> infoById(Long id) {
		try {
			return BaseResponse.success(sysRoleService.selectPKWithAssociate(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSROLE_QUERY_INFO_ERROR);
		}
	}

}
