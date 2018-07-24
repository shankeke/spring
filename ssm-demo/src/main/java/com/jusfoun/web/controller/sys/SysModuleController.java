package com.jusfoun.web.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.annotation.Logable;
import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.entity.SysModule;
import com.jusfoun.service.SysModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述 :权限管理接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午5:37:13
 */
@Api(description = "权限维护管理", value = "权限维护管理接口类")
@RestController
@RequestMapping(value = {"/sysmodule/", "/app/sysmodule/"})
public class SysModuleController  {
	@Autowired
	private SysModuleService sysModuleService;

	/**
	 * 描述 : 权限树列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午5:38:18
	 * @return
	 */
	@ApiOperation(value = "查询权限列表信息", notes = "查询权限列表信息", hidden = false)
	@Logable(desc = "权限列表", fullPath = "系统管理/权限管理/权限列表")
	// @PreAuthorize("hasPermission('user', '/sysmodule/list')")
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public BaseResponse<List<SysModule>> list(@ApiParam(value = "查询的根节点的ID", defaultValue = "0") @RequestParam(defaultValue = "0") Long parentId) {
		try {
			return BaseResponse.success(sysModuleService.selectByParentId(parentId));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSMODULE_QUERY_LIST_ERROR);
		}
	}

}
