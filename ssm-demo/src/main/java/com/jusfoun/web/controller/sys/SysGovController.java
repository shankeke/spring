package com.jusfoun.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.base.tree.BaseTreeableController;
import com.jusfoun.common.base.tree.TreeableService;
import com.jusfoun.entity.SysGov;
import com.jusfoun.service.SysGovService;

import io.swagger.annotations.Api;

/**
 * 描述 : 组织机构管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:07:10
 */
@Api(description = "组织机构管理", value = "组织机构管理接口类")
@RestController
@RequestMapping("/sysgov")
public class SysGovController extends BaseController<SysGov, Long> implements BaseTreeableController<SysGov, Long> {

	@Autowired
	private SysGovService sysGovService;

	@Override
	public TreeableService<SysGov, Long> getTreeableService() {
		return sysGovService;
	}

}
