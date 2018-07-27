package com.jusfoun.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.base.tree.BaseTreeableAndIdableController;
import com.jusfoun.common.base.tree.TreeableAndIdableService;
import com.jusfoun.entity.SysModule;
import com.jusfoun.service.SysModuleService;

import io.swagger.annotations.Api;

/**
 * 描述 :权限管理接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午5:37:13
 */
@Api(description = "权限维护管理", value = "权限维护管理接口类")
@RestController
@RequestMapping(value = {"/sysmodule/"})
public class SysModuleController extends BaseController<SysModule, Long> implements BaseTreeableAndIdableController<SysModule, Long> {

	@Autowired
	private SysModuleService sysModuleService;

	@Override
	public TreeableAndIdableService<SysModule, Long> getTreeableIdableService() {
		return sysModuleService;
	}

 
}
