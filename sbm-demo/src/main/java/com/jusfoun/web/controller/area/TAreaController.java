package com.jusfoun.web.controller.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.controller.BaseController;
import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.base.tree.BaseTreeableAndIdableController;
import com.jusfoun.common.base.tree.TreeableAndIdableService;
import com.jusfoun.entity.TArea;
import com.jusfoun.service.TAreaService;

import io.swagger.annotations.Api;

@Api(description = "地区信息管理", value = "地区信息管理接口类")
@RestController
@RequestMapping("/area")
public class TAreaController implements BaseController<TArea>, BaseTreeableAndIdableController<TArea, Long> {

	@Autowired
	private TAreaService tAreaService;

	@Override
	public BaseService<TArea> getBaseService() {
		return tAreaService;
	}

	@Override
	public TreeableAndIdableService<TArea, Long> getTreeableIdableService() {
		return tAreaService;
	}

}
