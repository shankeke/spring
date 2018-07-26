package com.jusfoun.web.controller.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.base.tree.BaseTreeableController;
import com.jusfoun.common.base.tree.TreeableService;
import com.jusfoun.entity.TArea;
import com.jusfoun.service.TAreaService;

import io.swagger.annotations.Api;

@Api(description = "地区信息管理", value = "地区信息管理接口类")
@RestController
@RequestMapping("/area")
public class TAreaController extends BaseController<TArea, Long> implements BaseTreeableController<TArea, Long> {

	@Autowired
	private TAreaService tAreaService;

	@Override
	public TreeableService<TArea, Long> getTreeableService() {
		return tAreaService;
	}

}
