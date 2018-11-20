package com.jusfoun.web.controller.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.tree.BaseTreeableSelectController;
import com.jusfoun.common.base.tree.TreeableAndIdableService;
import com.jusfoun.entity.TArea;
import com.jusfoun.service.TAreaService;

import io.swagger.annotations.Api;

@Api(tags = "COMMON-TAreaController", description = "地区信息管理", value = "地区信息管理接口类")
@RestController
@RequestMapping(value = {"/v1/area", "/app/area"})
public class TAreaController implements BaseTreeableSelectController<TArea, Long> {

	@Autowired
	private TAreaService tAreaService;

	@Override
	public TreeableAndIdableService<TArea, Long> getTreeableIdableService() {
		return tAreaService;
	}
}
