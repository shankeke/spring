package com.jusfoun.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.controller.BasePageableAndIdableController;
import com.jusfoun.common.base.service.BaseIdableService;
import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.base.tree.BaseTreeableAndIdableController;
import com.jusfoun.common.base.tree.TreeableAndIdableService;
import com.jusfoun.entity.SysPrivs;
import com.jusfoun.service.SysPrivsService;

import io.swagger.annotations.Api;

/**
 * 说明：权限管理接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午5:37:13
 */
@Api(tags = "SYS-SysPrivsController", description = "权限维护管理", value = "权限维护管理接口类")
@RestController
@RequestMapping(value = {"/v1/sysprivs", "/app/sysprivs"})
public class SysPrivsController implements BasePageableAndIdableController<SysPrivs, Long>, BaseTreeableAndIdableController<SysPrivs, Long> {

	@Autowired
	private SysPrivsService sysPrivsService;

	@Override
	public BaseService<SysPrivs> getBaseService() {
		return sysPrivsService;
	}

	@Override
	public TreeableAndIdableService<SysPrivs, Long> getTreeableIdableService() {
		return sysPrivsService;
	}

	@Override
	public BaseIdableService<SysPrivs> getBaseIdableService() {
		return sysPrivsService;
	}

}
